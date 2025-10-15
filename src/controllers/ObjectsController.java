package controllers;

import java.util.Scanner;

import Domain.Objects.ObjectDTO;
import interfaces.IRegisterData;

public class ObjectsController implements IRegisterData{
	private static final Scanner sc = new Scanner(System.in);

	public ObjectDTO registration() {
		ObjectDTO dto = new ObjectDTO();
		IO.println("------- Object Registration -------");
		IO.print("Type name: ");
		dto.type_name = sc.nextLine();
		IO.print("Object name: ");
		dto.object_name = sc.nextLine();
		IO.print("Status: ");
		dto.status = sc.nextLine();
		IO.println("------- Object Registration -------");

		return dto;
	}
}
