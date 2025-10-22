package controllers;

import java.util.Scanner;

import Domain.Maintenance.MaintenanceDTO;
import handlers.MaintenanceHandler;

public class MaintenenceController{
	private static final Scanner _sc = new Scanner(System.in);
	private static final MaintenanceHandler _handler = new MaintenanceHandler();

	public void registration() {
		MaintenanceDTO newDTO = dataEntry();
		showDTO(newDTO);

		try {
        _handler.Insert(newDTO);
      } catch (Exception e) {
          IO.println("Erro ao cadastrar manutenção: " + e.getMessage());
      }
	}

	private MaintenanceDTO dataEntry() {
		MaintenanceDTO dto = new MaintenanceDTO();
		IO.println("------- Maintenance Registration -------");
		IO.print("Performed by (User ID): ");
		dto.user_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Object ID: ");
		dto.object_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Service type: ");
		dto.service_type = _sc.nextLine().trim().toUpperCase();
		IO.print("Description: ");
		dto.description = _sc.nextLine().trim().toUpperCase();
		IO.println("----------------------------------------");
		
		return dto;
	}

	private void showDTO(MaintenanceDTO dto) {
		IO.println("======= Maintenance Data =======");
		IO.println("Performed by (User ID): " + dto.user_id);
		IO.println("Object id: " + dto.object_id);
		IO.println("Service type: " + dto.service_type);
		IO.println("Description: " + dto.description);
		IO.println("===============================");
	}

}
