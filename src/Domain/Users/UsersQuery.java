package Domain.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class UsersQuery{

    public List<UsersDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");
    
        List<UsersDTO> list = new ArrayList<>();
        String sqlBase = "SELECT id, name, email FROM users WHERE NOT deleted AND " + GetType(type, value) + " ORDER BY id ";
    
        try {
            ResultSet result = conn.runQuerySQL(sqlBase);
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

	public void Insert(UsersDTO user) throws DataBaseException {
		DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

		String sqlBase = " INSERT INTO users (name, email) " +
					"VALUES ('" + user.name + "','" + user.email + "') ";

		try {
			conn.runSQL(sqlBase);
            IO.println("Usuário inserido com sucesso!");
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
            case "2": // Nome
                return " name LIKE '%" + value + "%'";
            case "3": // Email
                return " email LIKE '%" + value + "%'";
            default:
                return " 1=1 ";
        }
    }

}
