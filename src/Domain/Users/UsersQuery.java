package Domain.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import lib.JDBCConnection;


public class UsersQuery {
   private static final String _sqlBase = "SELECT id, name, email FROM users ";

      public List<UsersDTO> fetchAll() {
         Connection conn = JDBCConnection.getConnection();
         List<UsersDTO> list = new ArrayList<>();
         String sql = _sqlBase + "ORDER BY id";

         try (PreparedStatement ps = conn.prepareStatement(sql);
              ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
               UsersDTO dto = new UsersDTO();
               dto.id = rs.getInt("id");
               dto.name = rs.getString("name");
               dto.email = rs.getString("email");
               list.add(dto);
            }
      } catch (SQLException e) {
         throw new RuntimeException("Erro ao buscar usuários: " + e.getMessage(), e);
      }
      return list;
   }
}
