package controllers;

import java.util.Scanner;

import Domain.Users.UsersDTO;
import interfaces.IRegisterData;

public class UsersController implements IRegisterData{

  private static Scanner _sc = new Scanner(System.in);
  
  public UsersDTO registration() {
    UsersDTO dto = new UsersDTO();
    
    IO.println("------- User Registration -------");
    IO.print("Name: ");
    dto.name = _sc.nextLine();
    IO.print("Email: ");
    dto.email = _sc.nextLine();
    IO.println("------- User Registration -------");

    return dto;
  } 
}
