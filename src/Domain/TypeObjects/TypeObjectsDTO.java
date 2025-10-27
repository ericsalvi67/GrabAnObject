package Domain.TypeObjects;

import lib.ERDataResources;

public class TypeObjectsDTO extends ERDataResources {

    public int id;
    public String type_name;
    public String description;

    public TypeObjectsDTO() {}

    public TypeObjectsDTO(int id, String type_name, String description) {
        this.id = id;
        this.type_name = type_name;
        this.description = description;
    }

    public void showDTO() {
        IO.println("======= Dados do Tipo de Objeto =======");
        IO.println("ID: " + this.id);
        IO.println("Nome do tipo: " + this.type_name);
        IO.println("Descrição: " + this.description);
        IO.println("========================================");
    }

}
