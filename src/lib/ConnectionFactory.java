package lib;

import java.sql.*;

import interfaces.IConnectionString;

/**
 * Fábrica reutilizável para abrir/fechar conexões e criar PreparedStatements.
 */
public class ConnectionFactory {
    private final IConnectionString conn;

    public ConnectionFactory(IConnectionString conn) {
        this.conn = conn;
    }

    public Connection open() throws SQLException {
        return DriverManager.getConnection(conn.getUrl(), conn.getUser(), conn.getPassword());
    }

    public void close(Connection c) {
        if (c != null) {
            try { c.close(); } catch (SQLException ignored) {}
        }
    }

    public PreparedStatement prepare(Connection c, String sql, Object... params) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps;
    }
}
