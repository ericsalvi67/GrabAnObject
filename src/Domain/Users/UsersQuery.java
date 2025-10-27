package Domain.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class UsersQuery{

	public void Insert(UsersDTO user) throws DataBaseException {
		DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

		String sql = "INSERT INTO users (name, email) " +
					"VALUES ('" + user.name + "','" + user.email + "')";

		try {
			conn.runSQL(sql);
            IO.println("Usuário inserido com sucesso!");
		} catch (DataBaseException e) {
			throw new RuntimeException("Erro ao executar inserção no banco: " + e.getMessage(), e);
		} finally {
			conn.closeConnection();
		}
    }

    public List<UsersDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        List<UsersDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, email FROM users WHERE not deleted " + GetType(type, value) + " ORDER BY id";

        try {
            ResultSet result = conn.runQuerySQL(sql);
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
                return " id = " + value;
            case "name":
                return " and name LIKE '%" + value + "%'";
            case "email":
                return " and email LIKE '%" + value + "%'";
            default:
                return " and 1=1 ";
        }
    }

}
