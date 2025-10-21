package Domain.TypeObjects;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class TypeObjectsQuery {
   public void Insert(TypeObjectsDTO typeObject) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sql = "INSERT INTO type_objects (type_name, description, deleted, last_modification) " +
                    "VALUES ('" + typeObject.type_name + "','" + typeObject.description + "', " + typeObject.deleted + ", '" + typeObject.last_modification + "')";

		try{
			conn.connectDataBase();
		} catch (DataBaseException e) {
			throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage(), e);
		}

        try {
            conn.runSQL(sql);
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao executar inserção no banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }
}
