package handlers;

import java.util.List;

import Domain.Objects.ObjectsDTO;
import Domain.Objects.ObjectsQuery;
import db.DataBaseException;

public class ObjectsHandler {
   private ObjectsQuery _query = new ObjectsQuery();

    public void Upsert(ObjectsDTO newDTO) throws Exception {
       if (newDTO.type_id <= 0)
            throw new IllegalArgumentException("ID do Tipo não pode estar em branco");
        if (newDTO.object_name.isBlank())
            throw new IllegalArgumentException("Nome do Objeto não pode estar em branco");
        if (!newDTO.status.contains("A") && !newDTO.status.contains("M") && !newDTO.status.contains("B") && !newDTO.status.contains("L"))
           throw new IllegalArgumentException("Status é inválido");

        _query.Upsert(newDTO);
    }

    public List<ObjectsDTO> Select(String type, String value) throws DataBaseException {
        if (type == null || value == null) {
            throw new IllegalArgumentException("Tipo e valor não podem ser nulos");
        }
        return _query.Select(type, value);
    }

    public void Delete(int id) throws DataBaseException {
        if (id <= 0 || id == 0) {
            throw new IllegalArgumentException("ID inválido para exclusão");
        }
        _query.Delete(id);
    }
}
