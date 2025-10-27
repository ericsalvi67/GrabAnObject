package controllers;

import java.util.*;

import Domain.TypeObjects.TypeObjectsDTO;
import handlers.TypeObjectsHandler;

public class TypeObjectsController{
    private static Scanner _sc = new Scanner(System.in);
    private static final TypeObjectsHandler _handler = new TypeObjectsHandler();

    public void registration() {
        TypeObjectsDTO newDTO = dataEntry();
        newDTO.showDTO();

        try {
        _handler.Insert(newDTO);
      } catch (Exception e) {
          IO.println("Erro ao cadastrar tipo de objeto: " + e.getMessage());
      }

    }

    private TypeObjectsDTO dataEntry() {
        TypeObjectsDTO dto = new TypeObjectsDTO();
        IO.println("------- Cadastro de Tipo -------");
        IO.print("Nome do tipo: ");
        dto.type_name = _sc.nextLine().trim().toUpperCase();
        IO.print("Descrição: ");
        dto.description = _sc.nextLine().trim().toUpperCase();
        IO.println("--------------------------------");

        return dto;
    }



    public void search() {
        String value = "";
        String type = "";

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

        
        switch (option) {
            case "1":
                type = "id";
                break;
            case "2":
                type = "type_name";
                break;
            case "3":
                type = "description";
                break;
            default:
                break;
        }

        try {
            List<TypeObjectsDTO> results = _handler.Search(type, value);
            IO.println("======= Resultados da Busca =======");
            IO.println(" ID |  Nome do Tipo  | Descrição");
            for (TypeObjectsDTO typeObject : results) {
                IO.println(String.format("%3s | %14s | %25s", typeObject.id, typeObject.type_name, typeObject.description));
            }
            IO.println("===================================");
        } catch (Exception e) {
            IO.println("Erro durante a busca: " + e.getMessage());
        }
    }
  
}