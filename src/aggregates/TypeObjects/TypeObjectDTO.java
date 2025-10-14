package aggregates.TypeObjects;


public class TypeObjectDTO implements IERBusinessKey {

    public int er_id;
    public String type_name;
    public String description;

    public DateTime last_modification;
    public boolean deleted;

    public typeObjectDTO(){}

    public typeObjectDTO(int er_id, String type_name, String description) {
        this.er_id = er_id;
        this.typeName = typeName;
        this.description = description;
    }

    public String getBusinessKey(){
        return er_id + "|" + typeName;
    }

}
