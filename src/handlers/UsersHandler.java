package handlers;

import Domain.Users.UsersDTO;
import Domain.Users.UsersQuery;

public class UsersHandler {
    private UsersQuery _query = new UsersQuery();

    public void Insert(UsersDTO newDTO) throws Exception {
        if (newDTO.name.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        if (newDTO.email.isBlank() || !newDTO.email.contains("@")) 
            throw new IllegalArgumentException("Email is invalid");
            
        _query.Insert(newDTO);
    }
}
