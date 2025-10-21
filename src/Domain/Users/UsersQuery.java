package Domain.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class UsersQuery{
    private static final String _sqlUpdate = "UPDATE users SET name = ?, email = ? WHERE id = ? ";
    private static final String _sqlDelete = "UPDATE users SET deleted = true WHERE id = ? ";

	public void Insert(UsersDTO user) throws DataBaseException {
		DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

		String sql = "INSERT INTO users (name, email, deleted, last_modification) " +
					"VALUES ('" + user.name + "','" + user.email + "', " + user.deleted + ", '" + user.last_modification + "')";

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

    public List<UsersDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        List<UsersDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, email FROM users WHERE not deleted and"+ GetType(type,value) +" ORDER BY id";

		try{
			conn.connectDataBase();
		} catch (DataBaseException e) {
			throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage(), e);
		}

        try {
            ResultSet result = conn.runQuerySQL(sql); // garante abrir conexão antes da consulta
            while (result.next()) {
                UsersDTO user = new UsersDTO();
                user.id = result.getInt("id");
                user.name = result.getString("name");
                user.email = result.getString("email");
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
        return list;
    }

    private String GetType(String type, String value) {
        switch (type.toLowerCase()) {
            case "id":
                return "id = " + value;
            case "name":
                return "name LIKE '%" + value + "%'";
            case "email":
                return "email LIKE '%" + value + "%'";
            default:
                return "1=0";
        }
    }

}
