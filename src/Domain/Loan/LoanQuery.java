package Domain.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class LoanQuery {

    public List<LoanDTO> Select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");
        List<LoanDTO> list = new ArrayList<>();
        String sql = "SELECT id, user_id, object_id, loan_date, returned FROM loans WHERE " + GetType(type, value) + " ORDER BY id";

        try {
            ResultSet result = conn.runQuerySQL(sql);
            while (result.next()) {
                LoanDTO dto = new LoanDTO();
                dto.id = result.getInt("id");
                dto.user_id = result.getInt("user_id");
                dto.object_id = result.getString("object_id");
                dto.loan_date = result.getTimestamp("loan_date");
                dto.returned = result.getBoolean("returned");
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimos: " + e.getMessage(), e);
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }

        return list;
    }

    public void Insert(LoanDTO loan) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");
        
        String sql = "INSERT INTO loans (user_id, object_id, loan_date) " +
                    " VALUES (" + loan.user_id + ", '" + loan.object_id + "', now())";

        try {
            conn.runSQL(sql);
            IO.println("Empréstimo registrado com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao inserir empréstimo no banco: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }

    public void Update(String id, LoanDTO loan) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sql = "UPDATE loans SET " +
                    "user_id = " + loan.user_id + ", " +
                    "object_id = '" + loan.object_id + "', " +
                    "returned = " + loan.returned + ", " +
                    "last_modification = now() " +
                    "WHERE id = " + id;

        try {
            conn.runSQL(sql);
            IO.println("Empréstimo atualizado com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao atualizar empréstimo: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }

    public void Delete(String id) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        String sql = "DELETE FROM loans WHERE id = " + id;

        try {
            conn.runSQL(sql);
            IO.println("Empréstimo excluído com sucesso!");
        } catch (DataBaseException e) {
            throw new RuntimeException("Erro ao excluir empréstimo: " + e.getMessage(), e);
        } finally {
            conn.closeConnection();
        }
    }

    private String GetType(String type, String value) {
        switch (type) {
            case "1":
                return " id IN (" + value + ")";
            case "2":
                return " user_id IN (" + value + ")";
            case "3":
                return " object_id LIKE '%" + value + "%'";
            case "4":
                return " loan_date = '" + value + "'";
            default:
                return " 1=1 ";
        }
    }
}