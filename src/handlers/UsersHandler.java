package handlers;

import java.util.List;

import Domain.Users.UsersDTO;
import Domain.Users.UsersQuery;
import db.DataBaseException;

public class UsersHandler {
    private UsersQuery _query = new UsersQuery();

    public void Insert(UsersDTO newDTO) throws Exception {
        if (newDTO.name.isBlank())
            throw new IllegalArgumentException("Nome não pode estar em branco");
        if (newDTO.email.isBlank() || !newDTO.email.contains("@")) 
            throw new IllegalArgumentException("Email é inválido");
            
        _query.Insert(newDTO);
    }

    public List<UsersDTO> Search(String type, String value) throws DataBaseException {
        if (type == null || value == null) {
            throw new IllegalArgumentException("Tipo e valor não podem ser nulos");
        }
        return _query.Select(type, value);
    }
}
