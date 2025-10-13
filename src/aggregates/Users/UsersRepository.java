package aggregates.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.IPostgreSQLConnector;

public class UsersRepository {

    private final IPostgreSQLConnector connector;
    private Connection connection;
    public UsersRepository(IPostgreSQLConnector connector) {
        this.connector = connector;
    }

    public void open(String url, String user, String password) throws SQLException {
        this.connection = connector.connect(url, user, password);
    }

    public void close() throws SQLException {
        if (this.connection != null) {
            connector.disconnect(this.connection);
            this.connection = null;
        }
    }

    public boolean isOpen() throws SQLException {
        return this.connection != null && !this.connection.isClosed();
    }

    public List<UsersDTO> getAllUsers() throws SQLException {
        if (!isOpen()) {
            throw new SQLException("Connection is not open. Call open(...) first.");
        }

        String sql = "SELECT id, name, email, created_at FROM users";
        List<UsersDTO> out = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                java.time.OffsetDateTime lastMod = null;
                try {
                    lastMod = rs.getObject("created_at", java.time.OffsetDateTime.class);
                } catch (AbstractMethodError | java.sql.SQLFeatureNotSupportedException e) {
                    java.sql.Timestamp ts = rs.getTimestamp("created_at");
                    if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                }

                out.add(new UsersDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    lastMod
                ));
            }
        }

        return out;
    }

    public UsersDTO getById(int id) throws SQLException {
        if (!isOpen()) throw new SQLException("Connection not open");
        String sql = "SELECT id, name, email, created_at FROM users WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    java.time.OffsetDateTime lastMod = null;
                    try { lastMod = rs.getObject("created_at", java.time.OffsetDateTime.class); } catch (Exception e) {
                        java.sql.Timestamp ts = rs.getTimestamp("created_at"); if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                    }
                    return new UsersDTO(rs.getInt("id"), rs.getString("name"), rs.getString("email"), lastMod);
                }
            }
        }
        return null;
    }

    public int insert(String name, String email) throws SQLException {
        String sql = "INSERT INTO users(name, email) VALUES(?, ?) RETURNING id";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}