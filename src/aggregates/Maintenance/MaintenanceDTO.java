package aggregates.Maintenance;

public class MaintenanceDTO implements IERBusinessKey {

    public int er_id;
    public String description;
    public int er_user_id;

    public DateTime last_modification;
    public boolean deleted;

    public MaintenanceDTO(int er_id, String description, int er_user_id) {
        this.er_id = er_id;
        this.description = description;
        this.er_user_id = er_user_id;
    }

    public String getBusinessKey(){
        return er_user_id + "|" + er_id;
    }

}
