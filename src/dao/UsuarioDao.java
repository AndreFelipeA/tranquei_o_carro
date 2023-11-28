package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.BancoDeDados;
import models.Usuario;
import models.Carro;

public class UsuarioDao {

    public static void salvarUsuario(String nome, String email, String senha) {
        try {
            Connection connection = BancoDeDados.getInstance().getConnection();

            // Preparar a declaração SQL para a inserção de um usuário
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, senha);

                // Executar a inserção
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Usuario buscarUsuarioPorEmail(String emailUsuario) {
        Usuario usuarioEncontrado = null;

        try {
            Connection connection = BancoDeDados.getInstance().getConnection();

            // Preparar a declaração SQL para a busca do usuário pelo email
            String sql = "SELECT * FROM usuarios WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar os parâmetros da declaração SQL
                preparedStatement.setString(1, emailUsuario);

                // Executar a consulta
                ResultSet resultSet = preparedStatement.executeQuery();

                // Verificar se o usuário foi encontrado
                if (resultSet.next()) {
                    // Recuperar os dados do usuário do ResultSet
                    Integer id = resultSet.getInt("id_usuario");
                    String nome = resultSet.getString("nome");
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");

                    // Criar um objeto Usuario com os dados recuperados
                    usuarioEncontrado = new Usuario(nome, email, senha, id);
                    ArrayList<Carro> carrosDoUsuario = CarroDao.buscarCarrosPorUsuario(id);
                    for (Carro carro : carrosDoUsuario) {
                        usuarioEncontrado.adicionarCarro(carro);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioEncontrado;
    }

}
