package aggregates.TypeObjects;

import java.util.Date;

public class TypeObjectDTO{

    public int id;
    public char type_type;
    public String description;

    public Date last_modification;
    public boolean deleted;

    public TypeObjectDTO() {}

    public TypeObjectDTO(int id, 
                         char type_type, 
                         String description, 
                         boolean deleted) {
        this.id = id;
        this.type_type = type_type;
        this.description = description;
        this.deleted = deleted;
    }

}
