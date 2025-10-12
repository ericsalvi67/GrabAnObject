package connections;

import java.sql.Connection;
import java.sql.SQLException;


public class DbManager {
    private final PostgreSQLConnector connector;
    private Connection connection;

    public DbManager(PostgreSQLConnector connector) {
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

}