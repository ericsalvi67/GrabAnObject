package handlers;

import java.sql.SQLException;
import java.util.List;
import aggregates.Objects.*;

import interfaces.IPostgreSQLConnector;

public class ObjectsHandler {
    private final IPostgreSQLConnector connector;

    public ObjectsHandler(IPostgreSQLConnector connector) {
        this.connector = connector;
    }

    public List<ObjectDTO> listAll(String url, String user, String password) throws SQLException {
        ObjectsQuery repo = new ObjectsQuery(connector);
        try {
            repo.open(url, user, password);
            return repo.getAllObjects();
        } finally {
            repo.close();
        }
    }
}
