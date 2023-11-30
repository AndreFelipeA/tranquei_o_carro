package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

import controllers.BancoDeDados;


public class InformacaoDoCarroDao {
    public void salvarInformacaoDoCarro(String situacao, String descricao, String dataManutencao, int idCarro) {
        try {
            // Preparar a declaração SQL para a inserção de uma InformacaoDoCarro
            Connection connection = BancoDeDados.getInstance().getConnection();

            String sql = "INSERT INTO InformacaoDoCarro (situacao, descricao, dataManutencao, carro_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setString(1, situacao);
                preparedStatement.setString(2, descricao);
                preparedStatement.setString(3, dataManutencao);
                preparedStatement.setInt(4, idCarro);

                // Executar a inserção
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
