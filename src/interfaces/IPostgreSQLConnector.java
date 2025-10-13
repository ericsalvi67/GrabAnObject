package infrastructure;

import java.sql.*;

public interface IPostgreSQLConnector {
    Connection connect(String url, String user, String password) throws SQLException;

    void disconnect(Connection connection) throws SQLException;

    ResultSet executeQuery(Connection connection, String sql, Object... params) throws SQLException;

    int executeUpdate(Connection connection, String sql, Object... params) throws SQLException;
}
