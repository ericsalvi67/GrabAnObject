package lib;

import java.sql.*;

public class JDBCConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/gaoDB";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USUARIO, SENHA);
                IO.println("Conectado ao PostgreSQL com sucesso!");
            }
        } catch (SQLException e) {
            IO.println("Erro ao conectar ao PostgreSQL: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                IO.println("Conexão com PostgreSQL encerrada.");
            }
        } catch (SQLException e) {
            IO.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
