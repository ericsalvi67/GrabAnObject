package controllers;

import java.util.*;

import Domain.Loan.LoanDTO;
import handlers.LoanHandler;
import lib.SearchValues;

public class LoanController {
    private static final Scanner _sc = new Scanner(System.in);
    private static final LoanHandler _handler = new LoanHandler();

    public void register() {
        LoanDTO newDTO = new LoanDTO();
        IO.println("------- Cadastro de Empréstimo -------");
        IO.print("Realizado por (ID do Usuário): ");
        newDTO.user_id = _sc.nextInt();
        _sc.nextLine();
        IO.print("ID(s) do(s) Objeto(s) (separados por vírgula): ");
        newDTO.object_id = _sc.nextLine().trim();
        IO.println("--------------------------------------");
        newDTO.showDTO();

        try {
            _handler.Insert(newDTO);
        } catch (Exception e) {
            IO.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    public void search() {
        SearchValues search = searchLoan("Busca");
        try {
            List<LoanDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum empréstimo encontrado para pesquisa.");
                return;
            }
            showLoan(results);
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }

    public void delete() {
        SearchValues search = searchLoan("Exclusão");
        try {
            List<LoanDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum empréstimo encontrado para exclusão.");
                return;
            }
            showLoan(results);
            IO.print("Digite o ID do empréstimo que deseja excluir: ");
            String id = _sc.nextLine().trim();
            _handler.Delete(id);
        } catch (Exception e) {
            IO.println("Erro ao excluir empréstimo: " + e.getMessage());
        }
    }

    public void update() {
        SearchValues search = searchLoan("Atualização");

        try {
            List<LoanDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum registro de manutenção encontrado para atualização.");
                return;
            }
			showLoan(results);

			LoanDTO newDTO = new LoanDTO();
            String id;
            if (!search.type.equals("1")) {
                IO.print("Digite o ID que deseja atualizar: ");
                id = _sc.nextLine().trim();
            } else {
                id = Integer.toString(results.get(0).id);
            }
            LoanDTO oldDTO = new LoanDTO();
            oldDTO = results.stream()
                        .filter(user -> Integer.toString(user.id).equals(id))
                        .findFirst()
                        .orElse(null);

            IO.println("Digite os novos dados do empréstimo (vazio para manter o dado):");

            IO.print("ID(s) do(s) Objeto(s) (atual: " + oldDTO.object_id + "): ");
            newDTO.object_id = _sc.nextLine().trim();

            _handler.Update(id, oldDTO, newDTO);
        } catch (Exception e) {
            IO.println("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }

    public void returnLoan() {
        SearchValues search = searchLoan("Devolução");

        try {
            List<LoanDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum empréstimo encontrado para devolução.");
                return;
            }
            showLoan(results);
            IO.print("Digite o ID do empréstimo que deseja devolver: ");
            String id = _sc.nextLine().trim();
            LoanDTO oldDTO = results.get(0);
            LoanDTO newDTO = new LoanDTO();
            newDTO.returned = true;

            _handler.Update(id, oldDTO, newDTO);
            IO.println("Empréstimo devolvido com sucesso!");
        } catch (Exception e) {
            IO.println("Erro ao processar devolução: " + e.getMessage());
        }
    }

    private void showLoan(List<LoanDTO> results) {
        IO.println("======= Resultados da Busca =======");
        IO.println(" ID | ID do Usuário | IDs dos Objetos | Devolvido | Data do Empréstimo ");
        for (LoanDTO loan : results) {
            IO.println(String.format("%3s | %12s | %16s | %9s | %20s", loan.id, loan.user_id, loan.object_id, loan.returned ? "Sim" : "Não", loan.loan_date.toString()));
        }
        IO.println("===================================");
    }

    private SearchValues searchLoan(String param) {
        SearchValues search = new SearchValues();
        IO.println("------- " + param + " de Empréstimo -------");
        IO.println("1. ID");
        IO.println("2. ID do Usuário");
        IO.println("3. ID(s) do(s) Objeto(s)");
        IO.print("Opção: ");
        search.type = _sc.nextLine().trim();
        if (search.type.equals("1") || search.type.equals("2") || search.type.equals("3")) {
            IO.print("Digite o valor da busca: ");
            search.value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- " + param + " de Empréstimo -------");
        return search;
    }
}
