package controllers;

import java.util.List;
import java.util.Scanner;

import Domain.Report.ReportDTO;
import handlers.ReportHandler;
import lib.SearchValues;

public class ReportController {
   private static final Scanner _sc = new Scanner(System.in);
   private static final ReportHandler _handler = new ReportHandler();

   public void generateReports() {
      SearchValues search = new SearchValues();

      try {
         IO.println("Qaual relatório você gostaria de gerar?");
         IO.println("1. Empréstimos Ativos");
         IO.println("2. Empréstimos por Usuário");
         IO.println("3. Empréstimos por período");
         IO.print("Escolha uma opção: ");
         search.type  = _sc.nextLine().trim();
         if (search.type.equals("2")) {
            IO.print("Digite o Id do Usuário: ");
            search.value = _sc.nextLine().trim().toUpperCase();
         }
         if (search.type.equals("3")) {
            IO.print("Digite a data inicial (AAAA-MM-DD): ");
            String startDate = _sc.nextLine().trim();
            IO.print("Digite a data final (AAAA-MM-DD): ");
            String endDate = _sc.nextLine().trim();
            search.value = startDate + ";" + endDate;
         }
         List<ReportDTO> results = _handler.fetchReportData(search.type, search.value);
         if (results.isEmpty()) {
            IO.println("Nenhum dado encontrado para o relatório.");
            return;
         }
         showReport(results);
      } catch (Exception e) {
         IO.println("Erro ao gerar relatório: " + e.getMessage());
      }
   }

   private void showReport(List<ReportDTO> list) {
		IO.println("======= Resultados da Busca =======");
		IO.println(" ID | Nome do Usuário | Nome do Objeto | Tipo do Objeto | Status de empréstimo | Data do Empréstimo ");
		for (ReportDTO report : list) {
				IO.println(String.format("%3s | %15s | %14s | %14s | %20s | %18s", report.loan_id, report.user_name, report.object_name, report.type_name, report.loan_status, report.loan_date));
		}
		IO.println("===================================");
	}
}
