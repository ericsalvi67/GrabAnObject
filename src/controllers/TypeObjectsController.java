package controllers;

import java.util.Scanner;

import Domain.TypeObjects.TypeObjectsDTO;

public class TypeObjectsController{
    private static Scanner _sc = new Scanner(System.in);
  
    public void registration() {
        TypeObjectsDTO newDTO = dataEntry();
        showDTO(newDTO);

    }

    private TypeObjectsDTO dataEntry() {
        TypeObjectsDTO dto = new TypeObjectsDTO();
        IO.println("------- Type Registration -------");
        IO.print("Type name: ");
        dto.type_name = _sc.nextLine().trim().toUpperCase();
        IO.print("Description: ");
        dto.description = _sc.nextLine().trim().toUpperCase();
        IO.println("---------------------------------");

        return dto;
    }

    private void showDTO(TypeObjectsDTO dto) {
        IO.println("======= Type Object Data =======");
        IO.println("Type name: " + dto.type_name);
        IO.println("Description: " + dto.description);
        IO.println("================================");
    }
  
}