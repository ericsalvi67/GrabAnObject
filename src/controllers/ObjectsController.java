package controllers;

import java.util.*;

import Domain.Objects.ObjectsDTO;
import handlers.ObjectsHandler;

public class ObjectsController {
	private static final Scanner _sc = new Scanner(System.in);
	private static final ObjectsHandler _handler = new ObjectsHandler();

	public void registration() {
		ObjectsDTO newDTO = dataEntry();
		newDTO.showDTO();

		try {
        _handler.Insert(newDTO);
      } catch (Exception e) {
          IO.println("Erro ao cadastrar objeto: " + e.getMessage());
      }

	}

	private ObjectsDTO dataEntry() {
		ObjectsDTO dto = new ObjectsDTO();
		IO.println("------- Cadastro de Objeto -------");
		IO.print("ID do Tipo: ");
		dto.type_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Nome do objeto: ");
		dto.object_name = _sc.nextLine().trim().toUpperCase();
		IO.print("Status (A-Ativo, M-Manutenção, Q-Quebrado, E-Emprestado): ");
		dto.status = _sc.nextLine().trim().toUpperCase();
		IO.println("----------------------------------");

		return dto;
	}

	public void search() {
		String value = "";
		String type = "";

		IO.println("------- Busca de Objeto -------");
		IO.println("Selecione o campo de busca:");
		IO.println("1. ID");
		IO.println("2. ID do tipo");
		IO.println("3. Nome do Objeto");
		IO.println("4. Status");
		IO.print("Opção: ");
		String option = _sc.nextLine().trim();
		if (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")) {
			IO.print("Digite o valor da busca: ");
			value = _sc.nextLine().trim().toUpperCase();
		}
		IO.println("------- Busca de Objeto -------");

		switch (option) {
            case "1":
                type = "id";
                break;
            case "2":
                type = "type_id";
                break;
            case "3":
                type = "object_name";
                break;
            case "4":
                type = "status";
                break;
            default:
                break;
        }

		try {
			List<ObjectsDTO> results = _handler.Search(type, value);
			IO.println("======= Resultados da Busca =======");
			IO.println(" ID | ID do Tipo | Nome do Objeto | Status");
			for (ObjectsDTO object : results) {
				IO.println(String.format("%3s | %10s | %14s | %5s", object.id, object.type_id, object.object_name, object.status));
			}
			IO.println("===================================");
		} catch (Exception e) {
			IO.println("Erro durante a busca: " + e.getMessage());
		}
	}
}
