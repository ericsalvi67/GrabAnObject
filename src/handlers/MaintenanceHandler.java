package handlers;

import Domain.Maintenance.MaintenanceDTO;
import Domain.Maintenance.MaintenanceQuery;


public class MaintenanceHandler {
    private MaintenanceQuery _query = new MaintenanceQuery();

    public void Insert(MaintenanceDTO newDTO) throws Exception {
        if (newDTO.user_id <= 0)
            throw new IllegalArgumentException("User ID cannot be blank");
        if (newDTO.object_id <= 0)
            throw new IllegalArgumentException("Object ID is invalid");
        if (newDTO.service_type.isBlank())
            throw new IllegalArgumentException("Service type cannot be blank");
        if (newDTO.description.isBlank())
            throw new IllegalArgumentException("Description cannot be blank");

        _query.Insert(newDTO);
    }
}

