package domain.Maintenance;

import java.sql.SQLException;
import java.util.List;

import infrastructure.IPostgreSQLConnector;

public class MaintenanceHandler {
    private final IPostgreSQLConnector connector;

    public MaintenanceHandler(IPostgreSQLConnector connector) {
        this.connector = connector;
    }

    public List<MaintenanceDTO> listAll(String url, String user, String password) throws SQLException {
        MaintenanceRepository repo = new MaintenanceRepository(connector);
        try {
            repo.open(url, user, password);
            return repo.getAllMaintenance();
        } finally {
            repo.close();
        }
    }
}
