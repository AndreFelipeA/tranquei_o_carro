import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {
    private static BancoDeDados instance;
    private Connection connection;

    private BancoDeDados() {
        // Configuração do banco de dados SQLite
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:banco_de_dados.db";
        
        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection(url);

            // Criar a tabela "usuarios" se não existir
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "email TEXT," +
                    "senha TEXT)");

            // Criar a tabela "carros" se não existir
            statement.execute("CREATE TABLE IF NOT EXISTS carros (" +
                    "id_carro INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "marca TEXT," +
                    "modelo TEXT," +
                    "ano INTEGER," +
                    "proprietario_id INTEGER," +
                    "FOREIGN KEY (proprietario_id) REFERENCES usuarios(id_usuario))");

            // Criar a tabela "VerificaLocalizacao" se não existir
            statement.execute("CREATE TABLE IF NOT EXISTS VerificaLocalizacao (" +
                    "id_localizacao INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "longitude TEXT," +
                    "latitude TEXT," +
                    "carro_id INTEGER," +
                    "FOREIGN KEY (carro_id) REFERENCES carros(id_carro))");

            // Criar a tabela "InformacaoDoCarro" se não existir
            statement.execute("CREATE TABLE IF NOT EXISTS InformacaoDoCarro (" +
                    "id_informacao INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "situacao TEXT," +
                    "descricao TEXT," +
                    "dataManutencao TEXT," +
                    "carro_id INTEGER," +
                    "FOREIGN KEY (carro_id) REFERENCES carros(id_carro))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized BancoDeDados getInstance() {
        if (instance == null) {
            instance = new BancoDeDados();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void salvarUsuario(String nome, String email, String senha) {
        try {
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

    public Usuario buscarUsuarioPorEmail(String emailUsuario) {
        Usuario usuarioEncontrado = null;

        try {
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
                    String nome = resultSet.getString("nome");
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");

                    // Criar um objeto Usuario com os dados recuperados
                    usuarioEncontrado = new Usuario(nome, email, senha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioEncontrado;
    }
}
