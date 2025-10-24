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

    public MaintenanceDTO(int id, int user_id, int object_id, String service_type, String description, Date performed_at, boolean deleted) {
        this.id = id;
        this.user_id = user_id;
        this.object_id = object_id;
        this.service_type = service_type;
        this.description = description;
        this.performed_at = performed_at;
        this.deleted = deleted;
    }

}
