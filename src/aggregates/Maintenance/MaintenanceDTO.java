package aggregates.Maintenance;

import java.util.Date;
public class MaintenanceDTO{

    public int er_id;
    public int er_user_id;
    public String description;

    public Date last_modification;
    public boolean deleted;

    public MaintenanceDTO(int er_id, 
                          int er_user_id, 
                          String description, 
                          boolean deleted) {
        this.er_id = er_id;
        this.er_user_id = er_user_id;
        this.description = description;
        this.deleted = deleted;
    }

}
