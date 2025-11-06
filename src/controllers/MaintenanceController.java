package controllers;

import java.util.*;

import Domain.Maintenance.MaintenanceDTO;
import handlers.MaintenanceHandler;
import lib.SearchValues;

public class MaintenanceController{
	private static final Scanner _sc = new Scanner(System.in);
	private static final MaintenanceHandler _handler = new MaintenanceHandler();

	public void register() {
        MaintenanceDTO newDTO = new MaintenanceDTO();
		IO.println("------- Cadastro de Manutenção -------");
		IO.print("Realizada por (ID do Usuário): ");
		newDTO.user_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("ID do Objeto: ");
		newDTO.object_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Tipo de serviço: ");
		newDTO.service_type = _sc.nextLine().trim().toUpperCase();
		IO.print("Descrição: ");
		newDTO.description = _sc.nextLine().trim().toUpperCase();
		IO.println("--------------------------------------");
		newDTO.showDTO();

		try {
			_handler.Insert(newDTO);
		} catch (Exception e) {
			IO.println("Erro ao cadastrar manutenção: " + e.getMessage());
      }
	}

	public void search() {
		SearchValues search = searchMaintenance("Busca");

		try {
            List<MaintenanceDTO> results = _handler.Select(search.type, search.value);
			if (results.isEmpty()) {
				IO.println("Nenhum registro de manutenção encontrado para pesquisa.");
				return;
			}
            showMaintenance(results);
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }

    public void delete() {
		SearchValues search = searchMaintenance("Exclusão");

		try {
			List<MaintenanceDTO> results = _handler.Select(search.type, search.value);
			if (results.isEmpty()) {
					IO.println("Nenhuma manutenção encontrada para exclusão.");
					return;
			}

			showMaintenance(results);

			IO.print("Digite o ID da manutenção que deseja excluir: ");
			String id = _sc.nextLine().trim();

			_handler.Delete(id);

		} catch (Exception e) {
			IO.println("Erro ao excluir manutenção: " + e.getMessage());
		}
    }

    public void update() {
		SearchValues search = searchMaintenance("Atualização");

        try {
            List<MaintenanceDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum registro de manutenção encontrado para atualização.");
                return;
            }
			showMaintenance(results);

			MaintenanceDTO newDTO = new MaintenanceDTO();
            IO.print("Digite o ID do usuário que deseja atualizar: ");
            String id = _sc.nextLine().trim();
            MaintenanceDTO oldDTO = new MaintenanceDTO();
            oldDTO = (results.get(Integer.parseInt(id)));

            IO.println("Digite os novos dados da manutenção (vazio para manter o dado):");
            IO.print("Tipo de Serviço (atual: " + results.get(0).service_type + "): ");
            newDTO.service_type = _sc.nextLine().trim();

            IO.print("Descrição (atual: " + results.get(0).description + "): ");
            newDTO.description = _sc.nextLine().trim();

            IO.print("Status (A-Ativa, E-Encerrada) (atual: " + (!results.get(0).on_maintenance ? "E-Encerrada" : "A-Ativa") + "): ");
            newDTO.on_maintenance = (_sc.nextLine().trim().equals("A")) ? true : false;

            _handler.Update(id, oldDTO, newDTO);

        } catch (Exception e) {
            IO.println("Erro ao excluir manutenção: " + e.getMessage());
        }
    }

    private void showMaintenance(List<MaintenanceDTO> results) {
        IO.println("======= Resultados da Busca =======");
		IO.println(" Situação | ID | ID do Usuário | ID do Objeto | Realizada em | Tipo de Serviço | Descrição");
		for (MaintenanceDTO maintenance : results) {
			IO.println(String.format("%7s | %3s | %12s | %12s | %15s | %25s", 
				!maintenance.on_maintenance ? "Encerrada" : "Ativa", maintenance.id, maintenance.user_id, maintenance.object_id, maintenance.performed_at, maintenance.service_type, maintenance.description));
		}
		IO.println("===================================");
    }

	private SearchValues searchMaintenance(String param) 
    {
        SearchValues search = new SearchValues();

        IO.println("------- "+ param +" de Manutenção -------");
        IO.println("1. ID");
		IO.println("2. ID do Usuário");
		IO.println("3. ID do Objeto");
		IO.println("4. Tipo de Serviço");
		IO.println("5. Descrição");
		IO.print("Opção: ");
		search.type = _sc.nextLine().trim();
		if (search.type.equals("1") || search.type.equals("2") || search.type.equals("3") || search.type.equals("4") || search.type.equals("5")) {
			IO.print("Digite o valor da busca: ");
			search.value = _sc.nextLine().trim().toUpperCase();
		}
        IO.println("------- "+ param +" de Manutenção -------");

        return search;
    }
}    

