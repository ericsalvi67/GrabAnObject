package aggregates.Users;

import java.util.Date;

public class UsersDTO{

    public int id;
    public String name;
    public String email;

    public Date last_modification;
    public boolean deleted;

    public UsersDTO(){}

    public UsersDTO(int id, 
                    String name, 
                    String email, 
                    boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deleted = deleted;
    }
}