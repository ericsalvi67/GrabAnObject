package controllers;

import java.util.*;

import Domain.TypeObjects.TypeObjectsDTO;
import handlers.TypeObjectsHandler;
import lib.SearchValues;

public class TypeObjectsController {
    private static Scanner _sc = new Scanner(System.in);
    private static final TypeObjectsHandler _handler = new TypeObjectsHandler();

    public void register() {
        TypeObjectsDTO newDTO = new TypeObjectsDTO();
        IO.println("------- Cadastro de Tipo -------");
        IO.print("Nome do tipo: ");
        newDTO.type_name = _sc.nextLine().trim().toUpperCase();
        IO.print("Descrição: ");
        newDTO.description = _sc.nextLine().trim().toUpperCase();
        IO.println("--------------------------------");
        newDTO.showDTO();

        try {
            _handler.Insert(newDTO);
        } catch (Exception e) {
          IO.println("Erro ao cadastrar tipo de objeto: " + e.getMessage());
        }
    }

    public void search() {
        SearchValues search = searchTypeObjects("Busca");

        try {
            List<TypeObjectsDTO> results = _handler.Select(search.type, search.value);
            if (results.isEmpty()) {
                IO.println("Nenhum tipo de objeto encontrado para pesquisa.");
                return;
            }
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
            String id;
            if (!search.type.equals("1")) {
                IO.print("Digite o ID que deseja atualizar: ");
                id = _sc.nextLine().trim();
            } else {
                id = Integer.toString(results.get(0).id);
            }
            TypeObjectsDTO oldDTO = new TypeObjectsDTO();
            oldDTO = results.stream()
                        .filter(user -> Integer.toString(user.id).equals(id))
                        .findFirst()
                        .orElse(null);

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