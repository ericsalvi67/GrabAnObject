package controllers;

import java.util.*;

import Domain.Users.UsersDTO;
import handlers.UsersHandler;
import lib.SearchValues;


public class UsersController{
    private static Scanner _sc = new Scanner(System.in);
    private static UsersHandler _handler = new UsersHandler();
    
    public void register() {
        UsersDTO newDTO = new UsersDTO();

        IO.println("------- Cadastro de Usuário -------");
        IO.print("Nome: ");
        newDTO.name = _sc.nextLine().trim().toUpperCase();
        IO.print("Email: ");
        newDTO.email = _sc.nextLine().trim().toUpperCase();
        IO.println("------- Cadastro de Usuário -------");

        newDTO.showDTO();

        try {
            _handler.Insert(newDTO);
        } catch (Exception e) {
            IO.println("Erro ao cadastrar usuário: " + e.getMessage());
      }
    }

    public void search() {
        SearchValues search = searchUser("Busca");

        try {
            List<UsersDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum usuário encontrado para pesquisa.");
                return;
            }

            showUsers(results);
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }

    public void delete() {
        SearchValues search = searchUser("Exclusão");

        try {
            List<UsersDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum usuário encontrado para exclusão.");
                return;
            }

            showUsers(results);

            IO.print("Digite o ID do usuário que deseja excluir: ");
            String id = _sc.nextLine().trim();

            _handler.Delete(id);

        } catch (Exception e) {
            IO.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    public void update() {
        SearchValues search = searchUser("Atualização");
        
        try {
            List<UsersDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum usuário encontrado para atualização.");
                return;
            }
            showUsers(results);

            UsersDTO newDTO = new UsersDTO();
            String id;
            if (!search.type.equals("1")) {
                IO.print("Digite o ID que deseja atualizar: ");
                id = _sc.nextLine().trim();
            } else {
                id = Integer.toString(results.get(0).id);
            }
            UsersDTO oldDTO = new UsersDTO();
            oldDTO = results.stream()
                        .filter(user -> Integer.toString(user.id).equals(id))
                        .findFirst()
                        .orElse(null);

            IO.println("Digite os novos dados do usuário (vazio para manter o dado):");
            IO.print("Nome (atual: " + oldDTO.name + "): ");
            newDTO.name = _sc.nextLine().trim().toUpperCase();
            
            IO.print("Email (atual: " + oldDTO.email + "): ");
            newDTO.email = _sc.nextLine().trim().toUpperCase();

            _handler.Update(id, oldDTO, newDTO);

        } catch (Exception e) {
            IO.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private void showUsers(List<UsersDTO> results) {
        IO.println("======= Resultados da Busca =======");
        IO.println(" ID |     Nome      |       Email       ");
        for (UsersDTO user : results) {
            IO.println(String.format("%3s | %20s | %25s", user.id, user.name, user.email));
        }
        IO.println("===================================");
    }

    private SearchValues searchUser(String param) 
    {
        SearchValues search = new SearchValues();

        IO.println("------- "+ param +" de Usuário -------");
        IO.println("Selecione o campo de busca:");
        IO.println("1. ID");
        IO.println("2. Nome");
        IO.println("3. Email");
        IO.print("Opção: ");
        search.type = _sc.nextLine().trim();
        if (search.type.equals("1") || search.type.equals("2") || search.type.equals("3")) {
            IO.print("Digite o valor da busca: ");
            search.value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- "+ param +" de Usuário -------");

        return search;
    }
}
