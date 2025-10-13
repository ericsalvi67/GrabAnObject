package connections;

import java.sql.*;

import infrastructure.IPostgreSQLConnector;

/**
 * Simple JDBC implementation of PostgreSQLConnector.
 * Note: caller is responsible for closing the returned ResultSet and the Connection.
 * Closing the Connection will also close associated Statements/PreparedStatements.
 */
public class PostgreSQLConnector implements IPostgreSQLConnector {

    public PostgreSQLConnector() {
        // Optional: force driver load for older JVMs
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // driver not available on classpath — caller will get SQLException on connect
        }
    }

    @Override
    public Connection connect(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void disconnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public ResultSet executeQuery(Connection connection, String sql, Object... params) throws SQLException {
        if (params == null || params.length == 0) {
            // simple Statement for no-parameter queries
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } else {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeQuery();
        }
    }

    @Override
    public int executeUpdate(Connection connection, String sql, Object... params) throws SQLException {
        if (params == null || params.length == 0) {
            try (Statement stmt = connection.createStatement()) {
                return stmt.executeUpdate(sql);
            }
        } else {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
                return ps.executeUpdate();
            }
        }
    }
}