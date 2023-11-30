package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

import models.Carro;
import models.VerificaLocalizacao;
import controllers.BancoDeDados;

public class CarroDao {

    public static void adicionarCarro(String marca, String modelo, int ano, int idProprietario) {
        try {
            Connection connection = BancoDeDados.getInstance().getConnection();

            // Preparar a declaração SQL para a inserção de um carro
            String sql = "INSERT INTO carros (marca, modelo, ano, proprietario_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setString(1, marca);
                preparedStatement.setString(2, modelo);
                preparedStatement.setInt(3, ano);
                preparedStatement.setInt(4, idProprietario);
                
                // Executar a inserção
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Carro> buscarCarrosPorUsuario(int idProprietario) {
        ArrayList<Carro> carrosDoUsuario = new ArrayList<>();

        try {
            Connection connection = BancoDeDados.getInstance().getConnection();

            // Preparar a declaração SQL para a busca de carros pelo ID do proprietário
            String sql = "SELECT carros.*, VerificaLocalizacao.longitude, VerificaLocalizacao.latitude " +
                         "FROM carros " +
                         "LEFT JOIN VerificaLocalizacao ON carros.id_carro = VerificaLocalizacao.carro_id " +
                         "WHERE carros.proprietario_id = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setInt(1, idProprietario);

                // Executar a consulta
                ResultSet resultSet = preparedStatement.executeQuery();

                // Iterar sobre os resultados e adicionar carros à lista
                while (resultSet.next()) {
                    int idCarro = resultSet.getInt("id_carro");
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    int ano = resultSet.getInt("ano");

                    // Recuperar a localização do ResultSet
                    Double longitude = resultSet.getDouble("longitude");
                    Double latitude = resultSet.getDouble("latitude");
                    VerificaLocalizacao localizacao = new VerificaLocalizacao(latitude, longitude);

                    // Criar um objeto Carro com os dados recuperados, incluindo a Localizacao
                    Carro carro = new Carro(marca, modelo, ano,idCarro,localizacao);
                    carrosDoUsuario.add(carro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carrosDoUsuario;
    }

    public static void deletarCarro(int idCarro) {
        try {
            // Preparar a declaração SQL para a exclusão de um carro
            String sql = "DELETE FROM carros WHERE id_carro = ?";
            Connection connection = BancoDeDados.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setInt(1, idCarro);

                // Executar a exclusão
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
