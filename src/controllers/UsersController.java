package controllers;

import java.util.*;

import Domain.Users.UsersDTO;
import handlers.UsersHandler;


public class UsersController{
    private static Scanner _sc = new Scanner(System.in);
    private static UsersHandler _handler = new UsersHandler();
    
    public void registration() {
      UsersDTO newDTO = dataEntry();
      newDTO.showDTO();

      try {
        _handler.Insert(newDTO);
      } catch (Exception e) {
          IO.println("Erro ao cadastrar usuário: " + e.getMessage());
      }
    }

    private UsersDTO dataEntry() {
      UsersDTO dto = new UsersDTO();
      IO.println("------- Cadastro de Usuário -------");
      IO.print("Nome: ");
      dto.name = _sc.nextLine().trim().toUpperCase();
      IO.print("Email: ");
      dto.email = _sc.nextLine().trim().toUpperCase();
      IO.println("-----------------------------------");

      return dto;
    }

    public void search() {
        String value = "";
        String type = "";

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

        switch (option) {
            case "1":
                type = "id";
                break;
            case "2":
                type = "name";
                break;
            case "3":
                type = "email";
                break;
            default:
                break;
        }

        try {
            List<UsersDTO> results = _handler.Search(type, value);
            IO.println("======= Resultados da Busca =======");
            IO.println(" ID |     Nome      |   Email");
            for (UsersDTO user : results) {
                IO.println(String.format("%3s | %13s | %20s", user.id, user.name, user.email));
            }
            IO.println("===================================");
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }
}
