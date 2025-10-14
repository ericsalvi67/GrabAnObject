package aggregates.Users;

public class UsersDTO implements IERBusinessKey {

    public int er_id;
    public String name;
    public String email;

    public DateTime last_modification;
    public boolean deleted;

    public usersDTO(){}

    public usersDTO(int er_id, String name, String email) {
        this.er_id = er_id;
        this.name = name;
        this.email = email;
    }

    public String getBusinessKey(){
        return email.substring(0, email.indexOf('@'));
    }
}