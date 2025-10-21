package controllers;

import java.util.*;
import java.sql.Date;

import Domain.Users.UsersDTO;
import handlers.UsersHandler;


public class UsersController{
    private static Scanner _sc = new Scanner(System.in);
    private static UsersHandler _handler = new UsersHandler();
    
    public void registration() {
      UsersDTO newDTO = dataEntry();

      try {
        _handler.Insert(newDTO);
      } catch (Exception e) {
          IO.println("Erro ao cadastrar usuário: " + e.getMessage());
      }
    }

    private UsersDTO dataEntry() {
      UsersDTO dto = new UsersDTO();
      IO.println("------- User Registration -------");
      IO.print("| Name: ");
      dto.name = _sc.nextLine().trim().toUpperCase();
      IO.print("| Email: ");
      dto.email = _sc.nextLine().trim().toLowerCase();
      IO.println("---------------------------------");

      return new UsersDTO(0, dto.name, dto.email, false, new Date(System.currentTimeMillis()));
    }

    private void showDTO(UsersDTO dto) {
      IO.println("======= User Data =======");
      IO.println("ID: " + dto.id);
      IO.println("Name: " + dto.name);
      IO.println("Email: " + dto.email);
      IO.println("========================");
    }
}
