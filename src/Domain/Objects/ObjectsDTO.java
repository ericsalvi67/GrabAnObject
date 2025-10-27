package Domain.Objects;

import lib.ERDataResources;

public class ObjectsDTO extends ERDataResources {

    public int id;
    public int type_id;
    public String object_name;
    public String status;

    public ObjectsDTO() {}

    public ObjectsDTO(int id, int type_id, String object_name, String status) {
        this.id = id;
        this.type_id = type_id;
        this.object_name = object_name;
        this.status = status;
    }

    public void showDTO() {
		IO.println("======= Dados do Objeto =======");
		IO.println("ID do Tipo: " + this.type_id);
		IO.println("Nome do objeto: " + this.object_name);
		IO.println("Status: " + this.status);
		IO.println("===============================");
	}
}
