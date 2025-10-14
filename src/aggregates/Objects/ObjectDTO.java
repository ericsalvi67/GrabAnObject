package aggregates.Objects;

import java.util.Date;

public class ObjectDTO{

    public int id;
    public int type_id;
    public char type_type;
    public String object_name;
    public char status;

    public Date lastModification;
    public boolean deleted;

    public ObjectDTO() {}

    public ObjectDTO(int id,
                     int type_id, 
                     char type_type, 
                     String object_name, 
                     char status, 
                     boolean deleted) {
        this.id = id;
        this.type_id = type_id;
        this.type_type = type_type;
        this.object_name = object_name;
        this.status = status;
        this.deleted = deleted;
    }
}
