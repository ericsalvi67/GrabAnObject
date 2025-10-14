package aggregates.Maintenance;

import java.util.Date;
public class MaintenanceDTO{

    public int id;
    public int user_id;
    public int object_id;
    public String user_repair;
    public String object_name;
    public char service_type;
    public String description;
    public Date inicial_date;

    public Date last_modification;
    public boolean deleted;

    public MaintenanceDTO() {}

    public MaintenanceDTO(int id, 
                          int user_id,
                          int object_id, 
                          String user_repair,
                          String object_name,
                          char service_type,
                          String description,
                          Date inicial_date, 
                          boolean deleted) {
        this.id = id;
        this.user_id = user_id;
        this.object_id = object_id;
        this.user_repair = user_repair;
        this.object_name = object_name;
        this.service_type = service_type;
        this.description = description;
        this.inicial_date = inicial_date;
        this.deleted = deleted;
    }

}
