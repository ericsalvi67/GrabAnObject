package controllers;

import java.util.*;

import Domain.Users.UsersDTO;
import handlers.UsersHandler;


public class UsersController{
    private static Scanner _sc = new Scanner(System.in);
    private static UsersHandler _handler = new UsersHandler();
    
    public void register() {
        UsersDTO dto = new UsersDTO();
        IO.println("------- Cadastro de Usuário -------");
        IO.print("Nome: ");
        dto.name = _sc.nextLine().trim().toUpperCase();
        IO.print("Email: ");
        dto.email = _sc.nextLine().trim().toUpperCase();
        IO.println("-----------------------------------");
        dto.showDTO();

        try {
            _handler.Insert(dto);
        } catch (Exception e) {
            IO.println("Erro ao cadastrar usuário: " + e.getMessage());
      }
    }

    public void search() {
        String value = "";

        IO.println("------- Busca de Usuário -------");
        IO.println("Selecione o campo de busca:");
        IO.println("1. ID");
        IO.println("2. Nome");
        IO.println("3. Email");
        IO.print("Opção: ");
        String option = _sc.nextLine().trim();
        if (option.equals("1") || option.equals("2") || option.equals("3")) {
            IO.print("Digite o valor da busca: ");
            value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- Busca de Usuário -------");

        try {
            List<UsersDTO> results = _handler.Select(option, value);
            showUsers(results);
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }

    public void delete() {
        String value = "";

        IO.println("------- Exclusão de Usuário -------");
        IO.println("Selecione o campo de busca:");
        IO.println("1. ID");
        IO.println("2. Nome");
        IO.println("3. Email");
        IO.print("Opção: ");
        String option = _sc.nextLine().trim();
        if (option.equals("1") || option.equals("2") || option.equals("3")) {
            IO.print("Digite o valor da busca: ");
            value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- Exclusão de Usuário -------");
        try {
            List<UsersDTO> results = _handler.Select(option, value);
            if (results.isEmpty()) {
                IO.println("Nenhum usuário encontrado para exclusão.");
                return;
            }
            showUsers(results);
            IO.print("Digite o ID do usuário que deseja excluir: ");
            int id = _sc.nextInt();
            _sc.nextLine();

            _handler.Delete(id);

        } catch (Exception e) {
            IO.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    public void update() {
        IO.println("------- Atualização de Usuário -------");
        IO.print("Busque por ID (recomendado consulta):");
        String value = _sc.nextLine().trim();
        _sc.nextLine();
        IO.println("------- Atualização de Usuário -------");
        try {
            List<UsersDTO> results = _handler.Select("1", value);
            if (results.isEmpty()) {
                IO.println("Nenhum usuário encontrado para atualização.");
                return;
            }
            showUsers(results);

            UsersDTO newDTO = new UsersDTO();
            IO.println("Digite os novos dados do usuário (vazios para manter):");
            IO.print("Nome (atual: " + results.get(0).name + "): ");
            String newName = _sc.nextLine().trim();
            newDTO.name = newName.isEmpty() ? results.get(0).name : newName.toUpperCase();
            IO.print("Email (atual: " + results.get(0).email + "): ");
            String newEmail = _sc.nextLine().trim();
            newDTO.email = newEmail.isEmpty() ? results.get(0).email : newEmail.toUpperCase();

            _handler.Insert(newDTO);

        } catch (Exception e) {
            IO.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    private void showUsers(List<UsersDTO> results) {
        IO.println("======= Resultados da Busca =======");
        IO.println(" Situação | ID |     Nome      |       Email       ");
        for (UsersDTO user : results) {
            IO.println(String.format("%7s | %3s | %20s | %25s", 
                user.deleted ? "Inativo" : "Ativo", user.id, user.name, user.email));
        }
        IO.println("===================================");
    }
}
