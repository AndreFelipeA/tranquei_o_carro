package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.BancoDeDados;
import models.VerificaLocalizacao;

public class VerificaLocalizacaoDao {
    public static void salvarVerificaLocalizacao(Double longitude, Double latitude, int idCarro) {
        try {
            // Preparar a declaração SQL para a inserção de uma VerificaLocalizacao
            String sql = "INSERT INTO VerificaLocalizacao (longitude, latitude, carro_id) VALUES (?, ?, ?)";
            Connection connection = BancoDeDados.getInstance().getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setDouble(1, longitude);
                preparedStatement.setDouble(2, latitude);
                preparedStatement.setInt(3, idCarro);

                // Executar a inserção
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VerificaLocalizacao obterLocalizacao(int idCarro) {
        VerificaLocalizacao localizacao = null;

        try {
            Connection connection = BancoDeDados.getInstance().getConnection();

            // Preparar a declaração SQL para a busca de localização pelo ID do carro
            String sql = "SELECT * FROM VerificaLocalizacao WHERE carro_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setInt(1, idCarro);

                // Executar a consulta
                ResultSet resultSet = preparedStatement.executeQuery();

                // Verificar se a localização foi encontrada
                if (resultSet.next()) {
                    // Recuperar os dados de localização do ResultSet
                    Double longitude = resultSet.getDouble("longitude");
                    Double latitude = resultSet.getDouble("latitude");

                    // Criar um objeto Localizacao com os dados recuperados
                     localizacao = new VerificaLocalizacao(latitude, longitude);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return localizacao;
    }
}
