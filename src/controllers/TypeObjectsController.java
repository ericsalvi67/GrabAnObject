package controllers;

import java.util.*;

import Domain.TypeObjects.TypeObjectsDTO;
import handlers.TypeObjectsHandler;

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
        String value = "";

        IO.println("------- Exclusão de Tipo de Objeto -------");
        IO.println("Selecione o campo de busca:");
        IO.println("1. ID");
        IO.println("2. Nome do tipo de objeto");
        IO.println("3. Descrição");
        IO.print("Opção: ");
        String option = _sc.nextLine().trim();
        if (option.equals("1") || option.equals("2") || option.equals("3")) {
            IO.print("Digite o valor da busca: ");
            value = _sc.nextLine().trim().toUpperCase();
        }
        IO.println("------- Exclusão de Tipo de Objeto -------");
        try {
            List<TypeObjectsDTO> results = _handler.Select(option, value);
            if (results.isEmpty()) {
                IO.println("Nenhum tipo de objeto encontrado para exclusão.");
                return;
            }
            showTypeObjects(results);
            IO.print("Digite o ID do tipo de objeto que deseja excluir: ");
            int id = _sc.nextInt();
            _sc.nextLine();

            _handler.Delete(id);

        } catch (Exception e) {
            IO.println("Erro ao excluir tipo de objeto: " + e.getMessage());
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
}