package Domain.TypeObjects;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class TypeObjectsQuery {
    
   public void Insert(TypeObjectsDTO typeObject) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sql = "INSERT INTO type_objects (type_name, description) " +
                    "VALUES ('" + typeObject.type_name + "','" + typeObject.description + "')";

        try {
            conn.runSQL(sql);
            IO.println("Tipo de objeto inserido com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao executar inserção no banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }
}
