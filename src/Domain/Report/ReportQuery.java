package Domain.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class ReportQuery {

    public List<ReportDTO> select(String type, String value) throws DataBaseException {
        DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

        List<ReportDTO> list = new ArrayList<>();
        String sql = "  SELECT  " +
                        "    l.id loan_id, " +
                        "    u.name user_name, " +
                        "    o.object_name, " +
                        "    t.type_name, " +
                        "    CASE  " +
                        "        WHEN l.returned = TRUE THEN 'Devolvido' " +
                        "        ELSE 'Emprestado' " +
                        "    END loan_status, " +
                        "    l.loan_date " +
                        "FROM loans l " +
                        "INNER JOIN users u ON u.id = l.user_id " +
                        "CROSS JOIN LATERAL unnest(string_to_array(l.object_id, ',')) AS obj(id_text) " +
                        "INNER JOIN objects o ON o.id = CAST(obj.id_text AS INT) " +
                        "INNER JOIN type_objects t ON t.id = o.type_id " +
                        "WHERE " + GetType(type, value) +
                        "ORDER BY l.loan_date DESC, u.name, o.object_name ";


        try {
            ResultSet result = conn.runQuerySQL(sql);
            while (result.next()) {
                ReportDTO report = new ReportDTO();
                report.loan_id = result.getInt("loan_id");
                report.user_name = result.getString("user_name");
                report.object_name = result.getString("object_name");
                report.type_name = result.getString("type_name");
                report.loan_status = result.getString("loan_status");
                report.loan_date = result.getDate("loan_date");
                list.add(report);
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
            case "1": // Empréstimos Ativos
                return " l.returned = FALSE ";
            case "2": // ID do Usuário
                return " l.user_id IN (" + value + ") ";
            case "3": // Período
                String[] dates = value.split(";");
                return " l.loan_date BETWEEN '" + dates[0] + "' AND '" + dates[1] + "' ";
            default:
                return " 1=1 ";
        }
    }
}
