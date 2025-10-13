package handlers;

import java.sql.SQLException;
import java.util.List;
import aggregates.Users.*;

import interfaces.IPostgreSQLConnector;

public class UsersHandler {
    private final IPostgreSQLConnector connector;

    public UsersHandler(IPostgreSQLConnector connector) {
        this.connector = connector;
    }

    public List<UsersDTO> listAll(String url, String user, String password) throws SQLException {
        UsersRepository repo = new UsersRepository(connector);
        try {
            repo.open(url, user, password);
            return repo.getAllUsers();
        } finally {
            repo.close();
        }
    }
}
