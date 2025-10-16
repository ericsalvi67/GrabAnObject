package controllers;

import java.util.Scanner;

import Domain.TypeObjects.TypeObjectDTO;
import interfaces.IRegisterData;

public class TypeObjectsController implements IRegisterData{
    private static Scanner _sc = new Scanner(System.in);
  
    public void registration() {
        TypeObjectDTO newDTO = dataEntry();
        showDTO(newDTO);

    }

    private TypeObjectDTO dataEntry() {
        TypeObjectDTO dto = new TypeObjectDTO();
        IO.println("------- Type Registration -------");
        IO.print("Type name: ");
        dto.type_name = _sc.nextLine().trim().toUpperCase();
        IO.print("Description: ");
        dto.description = _sc.nextLine().trim().toUpperCase();
        IO.println("---------------------------------");

        return dto;
    }

    private void showDTO(TypeObjectDTO dto) {
        IO.println("======= Type Object Data =======");
        IO.println("Type name: " + dto.type_name);
        IO.println("Description: " + dto.description);
        IO.println("================================");
    }
  
}