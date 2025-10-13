package aggregates.TypeObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.IPostgreSQLConnector;

public class TypeObjectsQuery {
    private final IPostgreSQLConnector connector;
    private Connection connection;

    public TypeObjectsQuery(IPostgreSQLConnector connector) {
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

    public List<TypeObjectDTO> getAllTypes() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            throw new SQLException("Connection not open");
        }

        String sql = "SELECT id, type_name, description, created_at FROM type_objects";
        List<TypeObjectDTO> out = new ArrayList<>();

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

                out.add(new TypeObjectDTO(rs.getInt("id"), rs.getString("type_name"), rs.getString("description"), lastMod));
            }
        }

        return out;
    }

    public TypeObjectDTO getById(int id) throws SQLException {
        if (this.connection == null || this.connection.isClosed()) throw new SQLException("Connection not open");
        String sql = "SELECT id, type_name, description, created_at FROM type_objects WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    java.time.OffsetDateTime lastMod = null;
                    try { lastMod = rs.getObject("created_at", java.time.OffsetDateTime.class); } catch (Exception e) {
                        java.sql.Timestamp ts = rs.getTimestamp("created_at"); if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                    }
                    return new TypeObjectDTO(rs.getInt("id"), rs.getString("type_name"), rs.getString("description"), lastMod);
                }
            }
        }
        return null;
    }

    public int insert(String typeName, String description) throws SQLException {
        String sql = "INSERT INTO type_objects(type_name, description) VALUES(?, ?) RETURNING id";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, typeName);
            ps.setString(2, description);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}
