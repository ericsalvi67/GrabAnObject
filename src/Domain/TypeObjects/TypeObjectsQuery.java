package Domain.TypeObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class TypeObjectsQuery {
    
   public void Upsert(TypeObjectsDTO typeObject) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sqlBase = " INSERT INTO type_objects (type_name, description) " +
            "VALUES ('" + typeObject.type_name + "','" + typeObject.description + "') " +
            "ON CONFLICT (type_name) DO UPDATE SET " +
            "  description = EXCLUDED.description";

        try {
            conn.runSQL(sqlBase);
            IO.println("Tipo de objeto inserido com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao executar inserção no banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }

    public List<TypeObjectsDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        List<TypeObjectsDTO> list = new ArrayList<>();
        String sql = "SELECT id, type_name, description FROM type_objects WHERE NOT deleted " + GetType(type, value) + " ORDER BY id";

        try {
            ResultSet result = conn.runQuerySQL(sql);
            while (result.next()) {
                TypeObjectsDTO typeObject = new TypeObjectsDTO();
                typeObject.id = result.getInt("id");
                typeObject.type_name = result.getString("type_name");
                typeObject.description = result.getString("description");
                list.add(typeObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tipos de objetos: " + e.getMessage(), e);
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
        return list;
    }

    public void Delete(int id) throws DataBaseException{
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

		String sqlBase = " DELETE FROM type_objects WHERE id = " + id;

		try {
			conn.runSQL(sqlBase);
            IO.println("Tipo de objeto deletado com sucesso!");
		} catch (DataBaseException e) {
			throw new RuntimeException("Erro ao executar exclusão no banco: " + e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}
    }

    private String GetType(String type, String value) {
        switch (type.toLowerCase()) {
            case "1": // ID
                return " AND id = " + value;
            case "2": // Nome do Tipo
                return " AND type_name LIKE '%" + value + "%'";
            case "3": // Descrição
                return " AND description LIKE '%" + value + "%'";
            default:
                return " AND 1=1 ";
        }
    }

}
