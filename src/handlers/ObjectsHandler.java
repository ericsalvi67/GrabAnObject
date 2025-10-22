package handlers;

import Domain.Objects.ObjectsDTO;
import Domain.Objects.ObjectsQuery;

public class ObjectsHandler {
   private ObjectsQuery _query = new ObjectsQuery();

   public void Insert(ObjectsDTO newDTO) throws Exception {
      if (newDTO.type_id <= 0)
           throw new IllegalArgumentException("Type ID cannot be blank");
       if (newDTO.object_name.isBlank())
           throw new IllegalArgumentException("Object Name cannot be blank");
       if (!newDTO.status.contains("A") && !newDTO.status.contains("M") && !newDTO.status.contains("R") && !newDTO.status.contains("L"))
           throw new IllegalArgumentException("Status is invalid");

       _query.Insert(newDTO);
   }
}
