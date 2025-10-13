package domain.Maintenance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import infrastructure.IPostgreSQLConnector;

public class MaintenanceRepository {
    private final IPostgreSQLConnector connector;
    private Connection connection;

    public MaintenanceRepository(IPostgreSQLConnector connector) {
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

    public List<MaintenanceDTO> getAllMaintenance() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            throw new SQLException("Connection not open");
        }

        String sql = "SELECT id, object_id, description, performed_by, performed_at FROM maintenance";
        List<MaintenanceDTO> out = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Integer performedBy = rs.getObject("performed_by") == null ? null : rs.getInt("performed_by");

                java.time.OffsetDateTime lastMod = null;
                try {
                    lastMod = rs.getObject("performed_at", java.time.OffsetDateTime.class);
                } catch (AbstractMethodError | java.sql.SQLFeatureNotSupportedException e) {
                    java.sql.Timestamp ts = rs.getTimestamp("performed_at");
                    if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                }

                out.add(new MaintenanceDTO(rs.getInt("id"), rs.getInt("object_id"), rs.getString("description"), performedBy, lastMod));
            }
        }

        return out;
    }

    public List<MaintenanceDTO> getMaintenanceForObject(int objectId) throws SQLException {
        if (this.connection == null || this.connection.isClosed()) throw new SQLException("Connection not open");
        String sql = "SELECT id, object_id, description, performed_by, performed_at FROM maintenance WHERE object_id = ?";
        List<MaintenanceDTO> out = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, objectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer performedBy = rs.getObject("performed_by") == null ? null : rs.getInt("performed_by");
                    java.time.OffsetDateTime lastMod = null;
                    try { lastMod = rs.getObject("performed_at", java.time.OffsetDateTime.class); } catch (Exception e) {
                        java.sql.Timestamp ts = rs.getTimestamp("performed_at"); if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                    }
                    out.add(new MaintenanceDTO(rs.getInt("id"), rs.getInt("object_id"), rs.getString("description"), performedBy, lastMod));
                }
            }
        }
        return out;
    }

    public int insert(int objectId, String description, Integer performedBy) throws SQLException {
        String sql = "INSERT INTO maintenance(object_id, description, performed_by) VALUES(?, ?, ?) RETURNING id";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, objectId);
            ps.setString(2, description);
            if (performedBy == null) ps.setNull(3, java.sql.Types.INTEGER); else ps.setInt(3, performedBy);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getInt(1); }
        }
        return -1;
    }

    public MaintenanceDTO getById(int id) throws SQLException {
        String sql = "SELECT id, object_id, description, performed_by, performed_at FROM maintenance WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Integer performedBy = rs.getObject("performed_by") == null ? null : rs.getInt("performed_by");
                    java.time.OffsetDateTime lastMod = null;
                    try { lastMod = rs.getObject("performed_at", java.time.OffsetDateTime.class); } catch (Exception e) {
                        java.sql.Timestamp ts = rs.getTimestamp("performed_at"); if (ts != null) lastMod = ts.toInstant().atOffset(java.time.ZoneOffset.UTC);
                    }
                    return new MaintenanceDTO(rs.getInt("id"), rs.getInt("object_id"), rs.getString("description"), performedBy, lastMod);
                }
            }
        }
        return null;
    }
}
