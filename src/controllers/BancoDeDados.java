package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {
    private static BancoDeDados instance;
    private Connection connection;

    private BancoDeDados() {
        // Configuração do banco de dados SQLite

        String url = "jdbc:sqlite:db/banco_de_dados.db";

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
                    "longitude REAL," +
                    "latitude REAL," +
                    "carro_id INTEGER," +
                    "FOREIGN KEY (carro_id) REFERENCES carros(id_carro))");

            // Criar a tabela "InformacaoDoCarro" se não existir
            statement.execute("CREATE TABLE IF NOT EXISTS InformacaoDoCarro (" +
                    "id_informacao INTEGER 1PRIMARY KEY AUTOINCREMENT," +
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

    public static void endConnection() {
        try {
            instance.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
