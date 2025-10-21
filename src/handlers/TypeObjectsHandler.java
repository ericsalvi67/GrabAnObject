package handlers;

import Domain.TypeObjects.TypeObjectsDTO;
import Domain.TypeObjects.TypeObjectsQuery;

public class TypeObjectsHandler {
   private TypeObjectsQuery _query = new TypeObjectsQuery();

    public void Insert(TypeObjectsDTO newDTO) throws Exception {
        if (newDTO.type_name.isBlank())
            throw new IllegalArgumentException("Type name cannot be blank");
        if (newDTO.description.isBlank()) 
            throw new IllegalArgumentException("Description cannot be blank");
            
        _query.Insert(newDTO);
    }
}
