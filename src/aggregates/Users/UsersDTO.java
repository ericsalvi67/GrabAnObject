package aggregates.Users;

import java.util.Date;

public class UsersDTO{

    public int er_id;
    public String name;
    public String email;

    public Date last_modification;
    public boolean deleted;

    public usersDTO(){}

    public usersDTO(int er_id, 
                    String name, 
                    String email, 
                    boolean deleted) {
        this.er_id = er_id;
        this.name = name;
        this.email = email;
        this.deleted = deleted;
    }
}