package Domain.Users;

import interfaces.ERDataResources;

public class UsersDTO extends ERDataResources {

    public int id;
    public String name;
    public String email;

    public UsersDTO(){}

    public UsersDTO(int id, String name, String email, boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deleted = deleted;
    }
}