package handlers;

import java.util.List;

import Domain.Loan.LoanDTO;
import Domain.Loan.LoanQuery;
import Domain.Objects.ObjectsDTO;
import Domain.Objects.ObjectsQuery;
import db.DataBaseException;

public class LoanHandler {
    private LoanQuery _query = new LoanQuery();
    private ObjectsQuery _objectQuery = new ObjectsQuery();

    public List<LoanDTO> Select(String type, String value) throws DataBaseException {
        if (type == null || value == null) {
            throw new IllegalArgumentException("Tipo e valor não podem ser nulos");
        }
        return _query.Select(type, value);
    }

    public void Insert(LoanDTO newDTO) throws Exception {
        if (newDTO.user_id <= 0)
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero");
        if (newDTO.object_id == null || newDTO.object_id.isBlank())
            throw new IllegalArgumentException("ID do objeto não pode estar em branco");

        List<ObjectsDTO> objects = _objectQuery.Select("1", newDTO.object_id);
        for (ObjectsDTO objectDTO : objects) {
            if(objectDTO.status.equals("M") || objectDTO.status.equals("L") || objectDTO.status.equals("B")) {
                throw new IllegalArgumentException("Objeto não pode passar por empréstimo");
            }
        }

        if (newDTO.object_id.split(",").length != objects.size()) {
            throw new IllegalArgumentException("Um ou mais IDs de objetos são inválidos");
        }
            
        _query.Insert(newDTO);
    }

    public void Update(String id, LoanDTO oldDTO, LoanDTO newDTO) throws Exception {
        newDTO.user_id = (newDTO.user_id <= 0) ? oldDTO.user_id : newDTO.user_id;
        newDTO.object_id = (newDTO.object_id == null || newDTO.object_id.isEmpty()) ? oldDTO.object_id : newDTO.object_id;

        if (newDTO.user_id <= 0)
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero");
        if (newDTO.object_id == null || newDTO.object_id.isBlank())
            throw new IllegalArgumentException("ID do objeto não pode estar em branco");

        _query.Update(id, newDTO);
    }

    public void Delete(String id) throws DataBaseException {
        if (id == null || id.trim().isEmpty() || Integer.parseInt(id) <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão");
        }
        _query.Delete(id);
    }
}
