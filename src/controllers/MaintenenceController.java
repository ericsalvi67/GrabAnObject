package controllers;

import java.util.Scanner;

import Domain.Maintenance.MaintenanceDTO;
import interfaces.IRegisterData;

public class MaintenenceController implements IRegisterData{
	private static final Scanner _sc = new Scanner(System.in);

	public void registration() {
		MaintenanceDTO newDTO = dataEntry();
		showDTO(newDTO);

	}

	private MaintenanceDTO dataEntry() {
		MaintenanceDTO dto = new MaintenanceDTO();
		IO.println("------- Maintenance Registration -------");
		IO.print("Performed by (User ID): ");
		dto.performed_by = _sc.nextLine();
		IO.print("Object name: ");
		dto.object_name = _sc.nextLine().trim().toUpperCase();
		IO.print("Service type: ");
		dto.service_type = _sc.nextLine().trim().toUpperCase();
		IO.print("Description: ");
		dto.description = _sc.nextLine().trim().toUpperCase();
		IO.println("----------------------------------------");
		
		return dto;
	}

	private void showDTO(MaintenanceDTO dto) {
		IO.println("======= Maintenance Data =======");
		IO.println("Performed by (User ID): " + dto.performed_by);
		IO.println("Object name: " + dto.object_name);
		IO.println("Service type: " + dto.service_type);
		IO.println("Description: " + dto.description);
		IO.println("===============================");
	}

}
