package aggregates.TypeObjects;

import java.util.Date;

public class TypeObjectDTO{

    public int er_id;
    public String type_name;
    public String description;

    public Date last_modification;
    public boolean deleted;

    public typeObjectDTO(){}

    public typeObjectDTO(int er_id, 
                        String type_name, 
                        String description, 
                        boolean deleted) {
        this.er_id = er_id;
        this.type_name = type_name;
        this.description = description;
        this.deleted = deleted;
    }

}
