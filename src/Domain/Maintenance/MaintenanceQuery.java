package Domain.Maintenance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<MaintenanceDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        List<MaintenanceDTO> list = new ArrayList<>();
        String sql = "SELECT id, user_id, object_id, service_type, description, performed_at FROM maintenance WHERE not deleted " + GetType(type, value) + " ORDER BY id";

        try {
            ResultSet result = conn.runQuerySQL(sql);
            while (result.next()) {
                MaintenanceDTO maintenance = new MaintenanceDTO();
                maintenance.id = result.getInt("id");
                maintenance.user_id = result.getInt("user_id");
                maintenance.object_id = result.getInt("object_id");
                maintenance.service_type = result.getString("service_type");
                maintenance.description = result.getString("description");
                maintenance.performed_at = result.getDate("performed_at");
                list.add(maintenance);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar manutenções: " + e.getMessage(), e);
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
        return list;
    }

    public void Delete(int id) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

		String sqlBase = " UPDATE maintenance SET deleted = TRUE WHERE id = " + id;

		try {
			conn.runSQL(sqlBase);
            IO.println("Manutenção deletada com sucesso!");
		} catch (DataBaseException e) {
			throw new RuntimeException("Erro ao executar exclusão no banco: " + e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}
    }

    private String GetType(String type, String value) {
        switch (type.toLowerCase()) {
            case "1": // ID
                return " and id = " + value;
            case "2": // ID do Usuário
                return " and user_id = " + value;
            case "3": // ID do Objeto
                return " and object_id = " + value;
            case "4": // Tipo de Serviço
                return " and service_type LIKE '%" + value + "%'";
            case "5": // Descrição
                return " and description LIKE '%" + value + "%'";
            default:
                return " and 1=1 ";
        }
    }
}
