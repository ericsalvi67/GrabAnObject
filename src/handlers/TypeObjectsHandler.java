package handlers;

import java.util.List;

import Domain.TypeObjects.TypeObjectsDTO;
import Domain.TypeObjects.TypeObjectsQuery;
import db.DataBaseException;

public class TypeObjectsHandler {
    private TypeObjectsQuery _query = new TypeObjectsQuery();
 
    public List<TypeObjectsDTO> Select(String type, String value) throws DataBaseException {
        if (type == null || value == null) {
            throw new IllegalArgumentException("Tipo e valor não podem ser nulos");
        }
        return _query.Select(type, value);
    }

    public void Insert(TypeObjectsDTO newDTO) throws Exception {
        if (newDTO.type_name.isBlank())
            throw new IllegalArgumentException("Nome do tipo não pode estar em branco");
        if (newDTO.description.isBlank()) 
            throw new IllegalArgumentException("Descrição não pode estar em branco");
             
        _query.Insert(newDTO);
    }

    public void Update(String id, TypeObjectsDTO oldDTO, TypeObjectsDTO newDTO) throws Exception {
        newDTO.type_name = newDTO.type_name.isEmpty() ? oldDTO.type_name : newDTO.type_name.toUpperCase();
        newDTO.description = newDTO.description.isEmpty() ? oldDTO.description : newDTO.description.toUpperCase();

        if (newDTO.type_name.isBlank())
            throw new IllegalArgumentException("Nome do tipo não pode estar em branco");
        if (newDTO.description.isBlank())
            throw new IllegalArgumentException("Descrição não pode estar em branco");

        _query.Update(id, newDTO);
    }

    public void Delete(String id) throws DataBaseException {
        if (id == null || id.trim().isEmpty() || Integer.parseInt(id) <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão");
        }
        _query.Delete(id);
    }
}
