package Domain.Maintenance;

import java.util.Date;

import lib.ERDataResources;
public class MaintenanceDTO extends ERDataResources{

    public int id;
    public int user_id;
    public int object_id;
    public String service_type;
    public String description;
    public Date performed_at;

    public MaintenanceDTO() {}

    public MaintenanceDTO(int id, int user_id, int object_id, String service_type, String description, Date performed_at) {
        this.id = id;
        this.user_id = user_id;
        this.object_id = object_id;
        this.service_type = service_type;
        this.description = description;
        this.performed_at = performed_at;
    }

    public void showDTO() {
		IO.println("======= Dados da Manutenção =======");
		IO.println("ID: " + this.id);
		IO.println("Realizada por (ID do Usuário): " + this.user_id);
		IO.println("ID do Objeto: " + this.object_id);
		IO.println("Tipo de serviço: " + this.service_type);
		IO.println("Descrição: " + this.description);
        IO.println("Realizada em: " + this.performed_at);
		IO.println("===================================");
	}

}
