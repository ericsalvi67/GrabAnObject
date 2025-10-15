package controllers;

import java.util.Scanner;

import Domain.TypeObjects.TypeObjectDTO;
import interfaces.IRegisterData;

public class TypeObjectsController implements IRegisterData{
  private static Scanner _sc = new Scanner(System.in);
  
  public TypeObjectDTO registration() {
    TypeObjectDTO dto = new TypeObjectDTO();
    
    IO.println("------- Type Registration -------");
    IO.print("Type name: ");
    dto.type_name = _sc.nextLine();
    IO.print("Description: ");
    dto.description = _sc.nextLine();
    IO.println("------- Type Registration -------");

    return dto;
  }
  
}