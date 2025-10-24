package Domain.Objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class ObjectsQuery {

   public void Insert(ObjectsDTO object) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sql = "INSERT INTO objects (type_id, object_name, status) " +
                    " VALUES ('" + object.type_id + "', '" + object.object_name + "', '" + object.status + "')";

        try {
            conn.runSQL(sql);
            IO.println("Objeto inserido com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao executar inserção no banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }
}
