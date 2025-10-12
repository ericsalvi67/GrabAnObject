package domain.Users;

import java.sql.SQLException;
import java.util.List;

import infrastructure.IPostgreSQLConnector;

public class UserHandler {
    private final IPostgreSQLConnector connector;

    public UserHandler(IPostgreSQLConnector connector) {
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
