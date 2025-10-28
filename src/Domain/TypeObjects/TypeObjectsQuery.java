package Domain.TypeObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class TypeObjectsQuery {
    
    public List<TypeObjectsDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        List<TypeObjectsDTO> list = new ArrayList<>();
        String sqlBase = "SELECT id, type_name, description FROM type_objects WHERE " + GetType(type, value) + " ORDER BY id, deleted desc ";

        try {
            ResultSet result = conn.runQuerySQL(sqlBase);
            while (result.next()) {
                TypeObjectsDTO typeObject = new TypeObjectsDTO();
                typeObject.id = result.getInt("id");
                typeObject.type_name = result.getString("type_name");
                typeObject.description = result.getString("description");
                typeObject.deleted = result.getBoolean("deleted");
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

    public void Insert(TypeObjectsDTO typeObject) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sqlBase = "INSERT INTO type_objects (type_name, description) " +
                    "VALUES ('" + typeObject.type_name + "','" + typeObject.description + "')";

        try {
            conn.runSQL(sqlBase);
            IO.println("Tipo de objeto inserido com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao executar inserção no banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }

    private String GetType(String type, String value) {
        switch (type.toLowerCase()) {
            case "1": // ID
                return " id = " + value;
            case "2": // Nome do Tipo
                return " type_name LIKE '%" + value + "%'";
            case "3": // Descrição
                return " description LIKE '%" + value + "%'";
            default:
                return " 1=1 ";
        }
    }
}
