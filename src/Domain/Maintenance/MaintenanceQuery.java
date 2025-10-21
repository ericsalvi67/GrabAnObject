package Domain.Maintenance;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class MaintenanceQuery {

   public void Insert(MaintenanceDTO maintenance) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sql = "INSERT INTO maintenance (id, user_id, object_id, performed_by, object_name, service_type, description, performed_at, deleted, last_modification) " +
                    "VALUES (" + maintenance.id + ", " + maintenance.user_id + ", " + maintenance.object_id + ", '" + maintenance.performed_by + "', '" + maintenance.object_name + "', '" + maintenance.service_type + "', '" + maintenance.description + "', '" + maintenance.performed_at + "', " + maintenance.deleted + ", '" + maintenance.last_modification + "')";

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
