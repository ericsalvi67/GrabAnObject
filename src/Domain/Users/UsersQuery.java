package Domain.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;



public class UsersQuery{
   private static final String _sqlBase = "SELECT id, name, email FROM users ";
   private static final String _sqlInsert = "INSERT INTO users (name, email) VALUES (?, ?) ";
   private static final String _sqlUpdate = "UPDATE users SET name = ?, email = ? WHERE id = ? ";
   private static final String _sqlDelete = "UPDATE users SET deleted = true WHERE id = ? ";

      public List<UsersDTO> select() throws DataBaseException {
         DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");
         conn.connectionTest();
         List<UsersDTO> list = new ArrayList<>();
         String sql = _sqlBase + "ORDER BY id";

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
            try { conn.closeConnection(); } catch (DataBaseException ignore) {}
         }
         return list;
      }
}
