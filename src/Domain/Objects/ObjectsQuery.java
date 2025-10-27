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

    public List<ObjectsDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        List<ObjectsDTO> list = new ArrayList<>();
        String sql = "SELECT id, type_id, object_name, status FROM objects WHERE not deleted " + GetType(type, value) + " ORDER BY id";

        try {
            ResultSet result = conn.runQuerySQL(sql);
            while (result.next()) {
                ObjectsDTO object = new ObjectsDTO();
                object.id = result.getInt("id");
                object.type_id = result.getInt("type_id");
                object.object_name = result.getString("object_name");
                object.status = result.getString("status");
                list.add(object);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar objetos: " + e.getMessage(), e);
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
        return list;
    }

    private String GetType(String type, String value) {
        switch (type.toLowerCase()) {
            case "1": // ID
                return " and id = " + value;
            case "2": // ID do Tipo
                return " and type_id = " + value;
            case "3": // Nome do Objeto
                return " and object_name LIKE '%" + value + "%'";
            case "4": // Status
                return " and status LIKE '%" + value + "%'";
            default:
                return " and 1=1 ";
        }
    }
}
