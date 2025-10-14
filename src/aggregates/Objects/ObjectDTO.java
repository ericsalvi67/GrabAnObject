package aggregates.Objects;

public class ObjectDTO implements IERBusinessKey {

    public int er_id;
    public int er_type_id;
    public String name;
    public char status;

    public DateTime lastModification;
    public boolean deleted;

    public objectDTO(int er_id, String name, int er_type_id, char status) {
        this.er_id = er_id;
        this.name = name;
        this.er_type_id = er_type_id;
        this.status = status;
    }
    public String getBusinessKey(){
        return er_type_id + "|" + name.replaceAll(" ", "");
    }
}
