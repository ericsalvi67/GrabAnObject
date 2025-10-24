package Domain.Maintenance;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class MaintenanceQuery {

   public void Insert(MaintenanceDTO maintenance) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sql = "INSERT INTO maintenance (user_id, object_id, service_type, description, performed_at) " +
                    " VALUES (" + maintenance.user_id + ", " + maintenance.object_id + ", '" + maintenance.service_type + "', '" + maintenance.description + "', now())";

        try {
            conn.runSQL(sql);
            IO.println("Manutenção inserida com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao executar inserção no banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }
}
