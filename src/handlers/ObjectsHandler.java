package handlers;

import java.sql.SQLException;
import java.util.List;
import aggregates.Objects.*;

import interfaces.IConnectionString;
import lib.ConnectionFactory;

public class ObjectsHandler {
    private final ConnectionFactory factory;

    public ObjectsHandler(IConnectionString conn) {
        this.factory = new ConnectionFactory(conn);
    }

    public List<ObjectDTO> listAll() throws SQLException {
        ObjectsQuery repo = new ObjectsQuery(factory);
        return repo.getAllObjects();
    }
}
