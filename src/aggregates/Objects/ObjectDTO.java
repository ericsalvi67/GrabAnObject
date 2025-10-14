package aggregates.Objects;

import java.util.Date;

public class ObjectDTO{

    public int er_id;
    public int er_type_id;
    public String name;
    public char status;

    public Date lastModification;
    public boolean deleted;

    public objectDTO(int er_id, 
                     int er_type_id, 
                    String name, 
                    char status, 
                    boolean deleted) {
        this.er_id = er_id;
        this.er_type_id = er_type_id;
        this.name = name;
        this.status = status;
        this.deleted = deleted;
    }
}
