package controllers;

import java.util.*;

import Domain.Objects.ObjectsDTO;
import handlers.ObjectsHandler;

public class ObjectsController {
	private static final Scanner _sc = new Scanner(System.in);
	private static final ObjectsHandler _handler = new ObjectsHandler();

	public void register() {
        ObjectsDTO dto = new ObjectsDTO();
		IO.println("------- Cadastro de Objeto -------");
		IO.print("ID do Tipo: ");
		dto.type_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Nome do objeto: ");
		dto.object_name = _sc.nextLine().trim().toUpperCase();
		IO.print("Status (A-Ativo, M-Manutenção, B-Baixado, L-Emprestado): ");
		dto.status = _sc.nextLine().trim().toUpperCase();
		IO.println("----------------------------------");
		dto.showDTO();

		try {
			_handler.Insert(dto);
		} catch (Exception e) {
			IO.println("Erro ao cadastrar objeto: " + e.getMessage());
      }
	}

	public void search() {
		String value = "";

		IO.println("------- Busca de Objeto -------");
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
		IO.println("------- Busca de Objeto -------");

		try {
			List<ObjectsDTO> results = _handler.Select(option, value);
			showObjects(results);
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
			List<ObjectsDTO> results = _handler.Select(option, value);
			if (results.isEmpty()) {
					IO.println("Nenhum tipo de objeto encontrado para exclusão.");
					return;
			}
			showObjects(results);
			IO.print("Digite o ID do tipo de objeto que deseja excluir: ");
			int id = _sc.nextInt();
			_sc.nextLine();

			_handler.Delete(id);

		} catch (Exception e) {
			IO.println("Erro ao excluir tipo de objeto: " + e.getMessage());
		}
    }

	public void update() {
        IO.println("------- Atualização de Objetos -------");
        IO.print("Busque por ID (recomendado consulta):");
        String value = _sc.nextLine().trim();
        IO.println("------- Atualização de Objetos -------");
        try {
            List<ObjectsDTO> results = _handler.Select("1", value);
            if (results.isEmpty()) {
                IO.println("Nenhum objeto encontrado para atualização.");
                return;
            }

            IO.println("===================================");
            ObjectsDTO newDTO = new ObjectsDTO();
            IO.println("Digite os novos dados do objeto (vazios para manter):");
            IO.print("ID do Tipo (atual: " + results.get(0).type_id + "): ");
            String newTypeId = _sc.nextLine().trim();
            newDTO.type_id = newTypeId.isEmpty() ? results.get(0).type_id : Integer.parseInt(newTypeId);
            IO.print("Nome do Objeto (atual: " + results.get(0).object_name + "): ");
            String newObjectName = _sc.nextLine().trim();
            newDTO.object_name = newObjectName.isEmpty() ? results.get(0).object_name : newObjectName.toUpperCase();
            IO.print("Status (atual: " + results.get(0).status + "): ");
            String newStatus = _sc.nextLine().trim();
            newDTO.status = newStatus.isEmpty() ? results.get(0).status : newStatus.toUpperCase();

            _handler.Update(value, newDTO);

        } catch (Exception e) {
            IO.println("Erro ao excluir objeto: " + e.getMessage());
        }
    }

	 private void showObjects(List<ObjectsDTO> list) {
			IO.println("======= Resultados da Busca =======");
			IO.println(" ID | ID do Tipo | Nome do Objeto | Status");
			for (ObjectsDTO object : list) {
				 IO.println(String.format("%3s | %10s | %14s | %5s", object.id, object.type_id, object.object_name, object.status));
			}
			IO.println("===================================");
	  }
}
