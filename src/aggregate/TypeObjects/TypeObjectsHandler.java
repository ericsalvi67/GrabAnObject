package domain.TypeObjects;

import java.sql.SQLException;
import java.util.List;

import infrastructure.IPostgreSQLConnector;

public class TypeObjectsHandler {
    private final IPostgreSQLConnector connector;

    public TypeObjectsHandler(IPostgreSQLConnector connector) {
        this.connector = connector;
    }

    public List<TypeObjectDTO> listAll(String url, String user, String password) throws SQLException {
        TypeObjectsRepository repo = new TypeObjectsRepository(connector);
        try {
            repo.open(url, user, password);
            return repo.getAllTypes();
        } finally {
            repo.close();
        }
    }
}
