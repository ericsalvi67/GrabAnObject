package controllers;

import java.util.*;

import Domain.TypeObjects.TypeObjectsDTO;
import handlers.TypeObjectsHandler;
import lib.SearchValues;

public class TypeObjectsController {
    private static Scanner _sc = new Scanner(System.in);
    private static final TypeObjectsHandler _handler = new TypeObjectsHandler();

    public void register() {
        TypeObjectsDTO dto = new TypeObjectsDTO();
        IO.println("------- Cadastro de Tipo -------");
        IO.print("Nome do tipo: ");
        dto.type_name = _sc.nextLine().trim().toUpperCase();
        IO.print("Descrição: ");
        dto.description = _sc.nextLine().trim().toUpperCase();
        IO.println("--------------------------------");
        dto.showDTO();

        try {
            _handler.Insert(dto);
        } catch (Exception e) {
          IO.println("Erro ao cadastrar tipo de objeto: " + e.getMessage());
        }
    }

    public void search() {
        String value = "";

        IO.println("------- Busca de Tipo de Objeto -------");
        IO.println("Selecione o campo de busca:");
		IO.println("1. ID");
		IO.println("2. Nome do Tipo");
		IO.println("3. Descrição");
		IO.print("Opção: ");
        String option = _sc.nextLine().trim();
        if (option.equals("1") || option.equals("2") || option.equals("3")) {
            IO.print("Digite o valor da busca: ");
            value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- Busca de Tipo de Objeto -------");

        try {
            List<TypeObjectsDTO> results = _handler.Select(option, value);
            showTypeObjects(results);
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }

    public void delete() {
        SearchValues search = searchTypeObjects("Exclusão");

        try {
            List<TypeObjectsDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum tipo de objeto encontrado para exclusão.");
                return;
            }

            showTypeObjects(results);

            IO.print("Digite o ID do tipo de objeto que deseja excluir: ");
            String id = _sc.nextLine().trim();

            _handler.Delete(id);

        } catch (Exception e) {
            IO.println("Erro ao excluir tipo de objeto: " + e.getMessage());
        }
    }

    public void update() {
        SearchValues search = searchTypeObjects("Atualização");

        try {
            List<TypeObjectsDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum tipo de objeto encontrado para atualização.");
                return;
            }
            showTypeObjects(results);

            TypeObjectsDTO newDTO = new TypeObjectsDTO();
            IO.print("Digite o ID do tipo que deseja atualizar: ");
            String id = _sc.nextLine().trim();
            TypeObjectsDTO oldDTO = new TypeObjectsDTO();
            oldDTO = (results.get(Integer.parseInt(id)));

            IO.println("Digite os novos dados do tipo de objeto (vazio para manter o dado):");
            IO.print("Nome (atual: " + oldDTO.type_name + "): ");
            newDTO.type_name = _sc.nextLine().trim();

            IO.print("Descrição (atual: " + oldDTO.description + "): ");
            newDTO.description = _sc.nextLine().trim();

            _handler.Update(id, oldDTO, newDTO);

        } catch (Exception e) {
            IO.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
    

    public void showTypeObjects(List<TypeObjectsDTO> list) {
        IO.println("======= Resultados da Busca =======");
        IO.println(" ID |  Nome do Tipo  | Descrição");
        for (TypeObjectsDTO typeObject : list) {
            IO.println(String.format("%3s | %14s | %25s", typeObject.id, typeObject.type_name, typeObject.description));
        }
        IO.println("===================================");
    }

    private SearchValues searchTypeObjects(String param) 
    {
        SearchValues search = new SearchValues();

        IO.println("------- "+ param +" de Tipo de Objeto -------");
        IO.println("Selecione o campo de busca:");
        IO.println("1. ID");
        IO.println("2. Nome do tipo de objeto");
        IO.println("3. Descrição");
        IO.print("Opção: ");
        search.type = _sc.nextLine().trim();
        if (search.type.equals("1") || search.type.equals("2") || search.type.equals("3")) {
            IO.print("Digite o valor da busca: ");
            search.value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- "+ param +" de Tipo de Objeto -------");

        return search;
    }
}