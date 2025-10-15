package controllers;

import java.util.Scanner;

import Domain.Maintenance.MaintenanceDTO;
import interfaces.IRegisterData;

public class MaintenenceController implements IRegisterData{
	private static final Scanner sc = new Scanner(System.in);

	public MaintenanceDTO registration() {
		MaintenanceDTO dto = new MaintenanceDTO();
		IO.println("------- Maintenance Registration -------");
		IO.print("Performed by (User ID): ");
		dto.performed_by = sc.nextLine();
		IO.print("Object name: ");
		dto.object_name = sc.nextLine();
		IO.print("Service type: ");
		dto.service_type = sc.nextLine();
		IO.print("Description: ");
		dto.description = sc.nextLine();
		IO.println("------- Maintenance Registration -------");

		return dto;
	}

}
