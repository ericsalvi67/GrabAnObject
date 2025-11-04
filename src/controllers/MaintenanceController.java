package controllers;

import java.util.*;

import Domain.Maintenance.MaintenanceDTO;
import handlers.MaintenanceHandler;

public class MaintenanceController{
	private static final Scanner _sc = new Scanner(System.in);
	private static final MaintenanceHandler _handler = new MaintenanceHandler();

	public void register() {
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
		dto.showDTO();

		try {
			_handler.Upsert(dto);
		} catch (Exception e) {
			IO.println("Erro ao cadastrar manutenção: " + e.getMessage());
      }
	}

	public void search() {
		String value = "";

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

		try {
            List<MaintenanceDTO> results = _handler.Select(option, value);
            showMaintenance(results);
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }

    public void delete() {
		String value = "";

		IO.println("------- Exclusão de Tipo de Objeto -------");
		IO.println("Selecione o campo de busca:");
		IO.println("1. ID");
		IO.println("2. ID do tipo");
		IO.println("3. Nome do Objeto");
		IO.println("4. Status (A-Disponível, M-Manutenção, B-Baixado, L-Emprestado)");
		IO.print("Opção: ");
		String option = _sc.nextLine().trim();
		if (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")) {
			IO.print("Digite o valor da busca: ");
			value = _sc.nextLine().trim().toUpperCase();
		}
		IO.println("------- Exclusão de Tipo de Objeto -------");
		try {
			List<MaintenanceDTO> results = _handler.Select(option, value);
			if (results.isEmpty()) {
					IO.println("Nenhum tipo de objeto encontrado para exclusão.");
					return;
			}
			showMaintenance(results);
			IO.print("Digite o ID do tipo de objeto que deseja excluir: ");
			int id = _sc.nextInt();
			_sc.nextLine();

			_handler.Delete(id);

		} catch (Exception e) {
			IO.println("Erro ao excluir tipo de objeto: " + e.getMessage());
		}
    }

    public void update() {
        IO.println("------- Atualização de Manutenção -------");
        IO.print("Busque por ID (recomendado consulta):");
        String value = _sc.nextLine().trim();
        _sc.nextLine();
        IO.println("------- Atualização de Manutenção -------");
        try {
            List<MaintenanceDTO> results = _handler.Select("1", value);
            if (results.isEmpty()) {
                IO.println("Nenhum registro de manutenção encontrado para atualização.");
                return;
            }
            showMaintenance(results);

            MaintenanceDTO newDTO = new MaintenanceDTO();
            IO.println("Digite os novos dados da manutenção (vazios para manter):");
            IO.print("Tipo de Serviço (atual: " + results.get(0).service_type + "): ");
            String newServiceType = _sc.nextLine().trim();
            newDTO.service_type = newServiceType.isEmpty() ? results.get(0).service_type : newServiceType;
            IO.print("Descrição (atual: " + results.get(0).description + "): ");
            String newDescription = _sc.nextLine().trim();
            newDTO.description = newDescription.isEmpty() ? results.get(0).description : newDescription;
            IO.print("Status (A-Ativa, E-Encerrada) (atual: " + (results.get(0).deleted ? "E-Encerrada" : "A-Ativa") + "): ");
            String newStatus = _sc.nextLine().trim();
            newDTO.deleted = newStatus.isEmpty() ? results.get(0).deleted : newStatus.equalsIgnoreCase("E-Encerrada");

            _handler.Upsert(newDTO);

        } catch (Exception e) {
            IO.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    private void showMaintenance(List<MaintenanceDTO> results) {
        IO.println("======= Resultados da Busca =======");
            IO.println(" Situação | ID | ID do Usuário | ID do Objeto | Realizada em | Tipo de Serviço | Descrição");
            for (MaintenanceDTO maintenance : results) {
                IO.println(String.format("%7s | %3s | %12s | %12s | %15s | %25s", 
					maintenance.deleted ? "Encerrada" : "Ativa", maintenance.id, maintenance.user_id, maintenance.object_id, maintenance.performed_at, maintenance.service_type, maintenance.description));
            }
            IO.println("===================================");
    }
}    

