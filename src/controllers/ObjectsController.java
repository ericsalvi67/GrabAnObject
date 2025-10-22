package controllers;

import java.util.Scanner;

import Domain.Objects.ObjectsDTO;
import handlers.ObjectsHandler;

public class ObjectsController {
	private static final Scanner _sc = new Scanner(System.in);
	private static final ObjectsHandler _handler = new ObjectsHandler();

	public void registration() {
		ObjectsDTO newDTO = dataEntry();
		showDTO(newDTO);

		try {
        _handler.Insert(newDTO);
      } catch (Exception e) {
          IO.println("Erro ao cadastrar objeto: " + e.getMessage());
      }

	}

	private ObjectsDTO dataEntry() {
		ObjectsDTO dto = new ObjectsDTO();
		IO.println("------- Object Registration -------");
		IO.print("Type ID: ");
		dto.type_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Object name: ");
		dto.object_name = _sc.nextLine().trim().toUpperCase();
		IO.print("Status (A-Active, M-Maintenance, B-Broken, L-Lent): ");
		dto.status = _sc.nextLine().trim().toUpperCase();
		IO.println("-----------------------------------");

		return dto;
	}

	private void showDTO(ObjectsDTO dto) {
		IO.println("======= Object Data =======");
		IO.println("Type ID: " + dto.type_id);
		IO.println("Object name: " + dto.object_name);
		IO.println("Status: " + dto.status);
		IO.println("=========================");
	}
}
