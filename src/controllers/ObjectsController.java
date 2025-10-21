package controllers;

import java.util.Scanner;

import Domain.Objects.ObjectsDTO;

public class ObjectsController {
	private static final Scanner _sc = new Scanner(System.in);

	public void registration() {
		ObjectsDTO newDTO = dataEntry();
		showDTO(newDTO);

	}

	private ObjectsDTO dataEntry() {
		ObjectsDTO dto = new ObjectsDTO();
		IO.println("------- Object Registration -------");
		IO.print("Type name: ");
		dto.type_name = _sc.nextLine().trim().toUpperCase();
		IO.print("Object name: ");
		dto.object_name = _sc.nextLine().trim().toUpperCase();
		IO.print("Status: ");
		dto.status = _sc.nextLine().trim().toUpperCase();
		IO.println("-----------------------------------");

		return dto;
	}

	private void showDTO(ObjectsDTO dto) {
		IO.println("======= Object Data =======");
		IO.println("Type name: " + dto.type_name);
		IO.println("Object name: " + dto.object_name);
		IO.println("Status: " + dto.status);
		IO.println("=========================");
	}
}
