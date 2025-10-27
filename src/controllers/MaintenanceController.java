package controllers;

import java.util.*;

import Domain.Maintenance.MaintenanceDTO;
import handlers.MaintenanceHandler;

public class MaintenanceController{
	private static final Scanner _sc = new Scanner(System.in);
	private static final MaintenanceHandler _handler = new MaintenanceHandler();

	public void registration() {
		MaintenanceDTO newDTO = dataEntry();
		newDTO.showDTO();

		try {
        _handler.Insert(newDTO);
      } catch (Exception e) {
          IO.println("Erro ao cadastrar manutenção: " + e.getMessage());
      }
	}

	private MaintenanceDTO dataEntry() {
		MaintenanceDTO dto = new MaintenanceDTO();
		IO.println("------- Cadastro de Manutenção -------");
		IO.print("Realizada por (ID do Usuário): ");
		dto.user_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("ID do Objeto: ");
		dto.object_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Tipo de serviço: ");
		dto.service_type = _sc.nextLine().trim().toUpperCase();
		IO.print("Descrição: ");
		dto.description = _sc.nextLine().trim().toUpperCase();
		IO.println("--------------------------------------");
		
		return dto;
	}

	public void search() {
		String value = "";
        String type = "";

		IO.println("------- Busca de Manutenção -------");
		IO.println("Selecione o campo de busca:");
		IO.println("1. ID");
		IO.println("2. ID do Usuário");
		IO.println("3. ID do Objeto");
		IO.println("4. Tipo de Serviço");
		IO.println("5. Descrição");
		IO.print("Opção: ");
		String option = _sc.nextLine().trim();
		if (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") || option.equals("5")) {
			IO.print("Digite o valor da busca: ");
			value = _sc.nextLine().trim().toUpperCase();
		}
		IO.println("------- Busca de Manutenção -------");


		switch(option) {
			case "1":
				type = "id";
				break;
			case "2":
				type = "user_id";
				break;
			case "3":
				type = "object_id";
				break;
			case "4":
				type = "service_type";
				break;
			case "5":
				type = "description";
				break;
            default:
                break;
        }

		try {
            List<MaintenanceDTO> results = _handler.Search(type, value);
            IO.println("======= Resultados da Busca =======");
            IO.println(" ID | ID do Usuário | ID do Objeto | Tipo de Serviço | Descrição");
            for (MaintenanceDTO maintenance : results) {
                IO.println(String.format("%3s | %13s | %12s | %15s | %25s", maintenance.id, maintenance.user_id, maintenance.object_id, maintenance.service_type, maintenance.description));
            }
            IO.println("===================================");
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }
}    

