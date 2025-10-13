package aggregates.Objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.IPostgreSQLConnector;

public class ObjectsRepository {
    private final IPostgreSQLConnector connector;
    private Connection connection;

    public ObjectsRepository(IPostgreSQLConnector connector) {
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

    public List<ObjectDTO> getAllObjects() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            throw new SQLException("Connection not open");
        }

        String sql = "SELECT id, name, type_id, created_by, created_at FROM objects";
        List<ObjectDTO> out = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Integer typeId = rs.getObject("type_id") == null ? null : rs.getInt("type_id");
                Integer createdBy = rs.getObject("created_by") == null ? null : rs.getInt("created_by");

                java.time.OffsetDateTime lastMod = null;
                try {
                    lastMod = rs.getObject("created_at", java.time.OffsetDateTime.class);
                } catch (AbstractMethodError | java.sql.SQLFeatureNotSupportedException e) {
                    java.sql.Timestamp ts = rs.getTimestamp("created_at");
                    if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                }

                out.add(new ObjectDTO(rs.getInt("id"), rs.getString("name"), typeId, createdBy, lastMod));
            }
        }

        return out;
    }

    public List<ObjectDTO> getObjectsByType(int typeId) throws SQLException {
        if (this.connection == null || this.connection.isClosed()) throw new SQLException("Connection not open");
        String sql = "SELECT id, name, type_id, created_by, created_at FROM objects WHERE type_id = ?";
        List<ObjectDTO> out = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, typeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer typeIdVal = rs.getObject("type_id") == null ? null : rs.getInt("type_id");
                    Integer createdBy = rs.getObject("created_by") == null ? null : rs.getInt("created_by");
                    java.time.OffsetDateTime lastMod = null;
                    try { lastMod = rs.getObject("created_at", java.time.OffsetDateTime.class); } catch (Exception e) {
                        java.sql.Timestamp ts = rs.getTimestamp("created_at"); if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                    }
                    out.add(new ObjectDTO(rs.getInt("id"), rs.getString("name"), typeIdVal, createdBy, lastMod));
                }
            }
        }
        return out;
    }

    public ObjectDTO getById(int id) throws SQLException {
        if (this.connection == null || this.connection.isClosed()) throw new SQLException("Connection not open");
        String sql = "SELECT id, name, type_id, created_by, created_at FROM objects WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Integer typeIdVal = rs.getObject("type_id") == null ? null : rs.getInt("type_id");
                    Integer createdBy = rs.getObject("created_by") == null ? null : rs.getInt("created_by");
                    java.time.OffsetDateTime lastMod = null;
                    try { lastMod = rs.getObject("created_at", java.time.OffsetDateTime.class); } catch (Exception e) {
                        java.sql.Timestamp ts = rs.getTimestamp("created_at"); if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                    }
                    return new ObjectDTO(rs.getInt("id"), rs.getString("name"), typeIdVal, createdBy, lastMod);
                }
            }
        }
        return null;
    }

    public int insert(String name, Integer typeId, Integer createdBy) throws SQLException {
        String sql = "INSERT INTO objects(name, type_id, created_by) VALUES(?, ?, ?) RETURNING id";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, name);
            if (typeId == null) ps.setNull(2, java.sql.Types.INTEGER); else ps.setInt(2, typeId);
            if (createdBy == null) ps.setNull(3, java.sql.Types.INTEGER); else ps.setInt(3, createdBy);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getInt(1); }
        }
        return -1;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM objects WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) { ps.setInt(1, id); return ps.executeUpdate() > 0; }
    }
}
