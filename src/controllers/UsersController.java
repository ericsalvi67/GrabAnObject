package controllers;

import java.util.List;
import java.util.Scanner;

import Domain.Users.*;
import interfaces.IRegisterData;

public class UsersController implements IRegisterData{
    private static Scanner _sc = new Scanner(System.in);
    
    public void registration() {
      UsersDTO newDTO = dataEntry();
      showDTO(newDTO);
      try {
        UsersQuery query = new UsersQuery();
        List<UsersDTO> users = query.select();
        for (UsersDTO user : users) {
          IO.println("Existing User - ID: " + user.id + ", Name: " + user.name + ", Email: " + user.email);
        }
      } catch (Exception e) {
          e.printStackTrace();
      }

    }

    private UsersDTO dataEntry() {
      UsersDTO dto = new UsersDTO();
      IO.println("------- User Registration -------");
      IO.print("Name: ");
      dto.name = _sc.nextLine().trim().toUpperCase();
      IO.print("Email: ");
      dto.email = _sc.nextLine().trim().toLowerCase();
      IO.println("---------------------------------");

      return dto;
    }

    private void showDTO(UsersDTO dto) {
      IO.println("======= User Data =======");
      IO.println("ID: " + dto.id);
      IO.println("Name: " + dto.name);
      IO.println("Email: " + dto.email);
      IO.println("========================");
    }
}
