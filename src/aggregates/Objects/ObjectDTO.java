package aggregates.Objects;

import interfaces.ERDataResources;

public class ObjectDTO extends ERDataResources {

    public int id;
    public int type_id;
    public String type_name;
    public String object_name;
    public String status;

    public ObjectDTO() {}

    public ObjectDTO(int id,
                     int type_id, 
                     String type_name, 
                     String object_name, 
                     String status, 
                     boolean deleted) {
        this.id = id;
        this.type_id = type_id;
        this.type_name = type_name;
        this.object_name = object_name;
        this.status = status;
        this.deleted = deleted;
    }
}
