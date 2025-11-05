package handlers;

import java.util.List;

import Domain.Maintenance.MaintenanceDTO;
import Domain.Maintenance.MaintenanceQuery;
import Domain.Objects.ObjectsDTO;
import Domain.Objects.ObjectsQuery;
import db.DataBaseException;


public class MaintenanceHandler {
    private MaintenanceQuery _query = new MaintenanceQuery();
    private ObjectsQuery _objectQuery = new ObjectsQuery();

    public List<MaintenanceDTO> Select(String type, String value) throws DataBaseException {
        if (type == null || value == null) {
            throw new IllegalArgumentException("Tipo e valor não podem ser nulos");
        }
        return _query.Select(type, value);
    }

    public void Insert(MaintenanceDTO newDTO) throws Exception {
        if (newDTO.user_id <= 0)
            throw new IllegalArgumentException("ID do Usuário não pode estar em branco");
        if (newDTO.object_id <= 0)
            throw new IllegalArgumentException("ID do Objeto é inválido");
        if (newDTO.service_type.isBlank())
            throw new IllegalArgumentException("Tipo de serviço não pode estar em branco");
        if (newDTO.description.isBlank())
            throw new IllegalArgumentException("Descrição não pode estar em branco");

        List<ObjectsDTO> objects = _objectQuery.Select("1", Integer.toString(newDTO.object_id));
        if(objects.get(0).status.equals("M") || objects.get(0).status.equals("L") || objects.get(0).status.equals("B")) {
            throw new IllegalArgumentException("Objeto não pode passar por manutenção");
        }

        _query.Insert(newDTO);
    }

    public void Update(String id, MaintenanceDTO newDTO) throws Exception {
        if (newDTO.service_type.isBlank())
            throw new IllegalArgumentException("Tipo de serviço não pode estar em branco");
        if (newDTO.description.isBlank())
            throw new IllegalArgumentException("Descrição não pode estar em branco");

        _query.Update(id, newDTO);
    }

    public void Delete(int id) throws DataBaseException {
        if (id <= 0 || id == 0) {
            throw new IllegalArgumentException("ID inválido para exclusão");
        }
        _query.Delete(id);
    }
}

