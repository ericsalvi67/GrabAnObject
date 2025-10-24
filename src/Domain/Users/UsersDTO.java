package Domain.Users;

import java.sql.Date;

import lib.ERDataResources;

public class UsersDTO extends ERDataResources {

    public int id;
    public String name;
    public String email;

    public UsersDTO(){}

    public UsersDTO(int id, String name, String email, boolean deleted, Date last_modification) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deleted = deleted;
        this.last_modification = last_modification;
    }
}