package controllers;

import java.util.*;

import Domain.Objects.ObjectsDTO;
import handlers.ObjectsHandler;
import lib.SearchValues;

public class ObjectsController {
	private static final Scanner _sc = new Scanner(System.in);
	private static final ObjectsHandler _handler = new ObjectsHandler();

	public void register() {
        ObjectsDTO newDTO = new ObjectsDTO();
		IO.println("------- Cadastro de Objeto -------");
		IO.print("ID do Tipo: ");
		newDTO.type_id = _sc.nextInt();
		_sc.nextLine();
		IO.print("Nome do objeto: ");
		newDTO.object_name = _sc.nextLine().trim().toUpperCase();
		IO.print("Status (A-Ativo, M-Manutenção, B-Baixado, L-Emprestado): ");
		newDTO.status = _sc.nextLine().trim().toUpperCase();
		IO.println("----------------------------------");
		newDTO.showDTO();

		try {
			_handler.Insert(newDTO);
		} catch (Exception e) {
			IO.println("Erro ao cadastrar objeto: " + e.getMessage());
      }
	}

	public void search() {
		SearchValues search = searchObjects("Busca");

		try {
			List<ObjectsDTO> results = _handler.Select(search.type, search.value);
			if (results.isEmpty()) {
                IO.println("Nenhum objeto encontrado para pesquisa.");
                return;
            }

			showObjects(results);
		} catch (Exception e) {
			IO.println("Erro durante a busca: " + e.getMessage());
		}
	}

	public void delete() {
		SearchValues search = searchObjects("Exclusão");

		try {
			List<ObjectsDTO> results = _handler.Select(search.type, search.value);
			if (results.isEmpty()) {
					IO.println("Nenhum tipo de objeto encontrado para exclusão.");
					return;
			}

			showObjects(results);

			IO.print("Digite o ID do tipo de objeto que deseja excluir: ");
			String id = _sc.nextLine().trim();

			_handler.Delete(id);

		} catch (Exception e) {
			IO.println("Erro ao excluir tipo de objeto: " + e.getMessage());
		}
    }

	public void update() {
		SearchValues search = searchObjects("Atualização");

        try {
            List<ObjectsDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum objeto encontrado para atualização.");
                return;
            }
			showObjects(results);

            ObjectsDTO newDTO = new ObjectsDTO();
			String id;
            if (!search.type.equals("1")) {
                IO.print("Digite o ID que deseja atualizar: ");
                id = _sc.nextLine().trim();
            } else {
                id = Integer.toString(results.get(0).id);
            }
            ObjectsDTO oldDTO = new ObjectsDTO();
            oldDTO = (results.get(Integer.parseInt(id)));

            IO.println("Digite os novos dados do objeto (vazio para manter o dado):");
            IO.print("ID do Tipo (atual: " + results.get(0).type_id + "): ");
            newDTO.type_id = _sc.nextInt();
			_sc.nextLine();
            
            IO.print("Nome do Objeto (atual: " + results.get(0).object_name + "): ");
            newDTO.object_name = _sc.nextLine().trim();

            IO.print("Status (atual: " + results.get(0).status + "): ");
            newDTO.status = _sc.nextLine().trim();

            _handler.Update(id, oldDTO, newDTO);

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

	private SearchValues searchObjects(String param) 
    {
        SearchValues search = new SearchValues();

        IO.println("------- "+ param +" de Objetos -------");
        IO.println("Selecione o campo de busca:");
        IO.println("1. ID");
		IO.println("2. ID do tipo");
		IO.println("3. Nome do Objeto");
		IO.println("4. Status (A-Disponível, M-Manutenção, B-Baixado, L-Emprestado)");
        IO.print("Opção: ");
        search.type = _sc.nextLine().trim();
        if (search.type.equals("1") || search.type.equals("2") || search.type.equals("3") || search.type.equals("4")) {
            IO.print("Digite o valor da busca: ");
            search.value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- "+ param +" de Objetos -------");

        return search;
    }
}
