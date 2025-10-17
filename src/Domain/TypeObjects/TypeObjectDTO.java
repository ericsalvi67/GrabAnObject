package Domain.TypeObjects;

import interfaces.ERDataResources;

public class TypeObjectDTO extends ERDataResources {

    public int id;
    public String type_name;
    public String description;

    public TypeObjectDTO() {}

    public TypeObjectDTO(int id, String type_name, String description, boolean deleted) {
        this.id = id;
        this.type_name = type_name;
        this.description = description;
        this.deleted = deleted;
    }

}
