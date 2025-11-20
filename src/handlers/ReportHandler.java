package handlers;

import java.util.List;

import Domain.Report.ReportDTO;
import Domain.Report.ReportQuery;

public class ReportHandler {
        private ReportQuery _query = new ReportQuery();

        public List<ReportDTO> fetchReportData(String type, String value) throws Exception{
            if (type == null || value == null) {
            throw new IllegalArgumentException("Tipo e valor não podem ser nulos");
            }   
            if (type.equals("3")){
                String[] dates = value.split(";");
                if (dates.length != 2) {
                    throw new IllegalArgumentException("Para o relatório por período, informe data inicial e final separadas por ponto e vírgula");
                }
                if (dates[0].isBlank() || dates[1].isBlank()) {
                    throw new IllegalArgumentException("Datas não podem estar em branco");
                }
                if (dates[0].length() != 10 || dates[1].length() != 10) {
                    throw new IllegalArgumentException("Datas devem estar no formato AAAA-MM-DD");
                }
                if (dates[0].compareTo(dates[1]) > 0) {
                    throw new IllegalArgumentException("Data inicial não pode ser maior que a data final");
                }
            }

            return _query.select(type, value);
        }

}
