package handlers;

import java.util.List;

import Domain.Users.UsersDTO;
import Domain.Users.UsersQuery;
import db.DataBaseException;

public class UsersHandler {
    private UsersQuery _query = new UsersQuery();

    public List<UsersDTO> Select(String type, String value) throws DataBaseException {
        if (type == null || value == null) {
            throw new IllegalArgumentException("Tipo e valor não podem ser nulos");
        }
        return _query.Select(type, value);
    }

    public void Insert(UsersDTO newDTO) throws Exception {
        if (newDTO.name.isBlank())
            throw new IllegalArgumentException("Nome não pode estar em branco");
        if (newDTO.email.isBlank() || !newDTO.email.contains("@")) 
            throw new IllegalArgumentException("Email é inválido");
            
        _query.Insert(newDTO);
    }

    public void Update(String id, UsersDTO oldDTO, UsersDTO newDTO) throws Exception {
        newDTO.email = newDTO.email.isEmpty() ? oldDTO.email : newDTO.email.toUpperCase();
        newDTO.name = newDTO.name.isEmpty() ? oldDTO.name : newDTO.name.toUpperCase();

        if (newDTO.name.isBlank())
            throw new IllegalArgumentException("Nome não pode estar em branco");
        if (newDTO.email.isBlank() || !newDTO.email.contains("@")) 
            throw new IllegalArgumentException("Email é inválido");
            
        _query.Update(id, newDTO);
    }

    public void Delete(String id) throws DataBaseException {
        if (id == null || id.trim().isEmpty() || Integer.parseInt(id) <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão");
        }
        _query.Delete(id);
    }
}
