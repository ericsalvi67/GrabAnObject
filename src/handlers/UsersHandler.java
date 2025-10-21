package handlers;

import Domain.Users.UsersDTO;
import Domain.Users.UsersQuery;

public class UsersHandler {
    private UsersQuery _query = new UsersQuery();

    public void Insert(UsersDTO newDTO) throws Exception {
        if (newDTO.name.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        if (newDTO.email.isBlank()) 
            throw new IllegalArgumentException("Email cannot be blank");
            
        _query.Insert(newDTO);
    }
}
