package tests;

import domain.Users.UsersDTO;
import domain.Objects.ObjectDTO;
import domain.TypeObjects.TypeObjectDTO;
import domain.Maintenance.MaintenanceDTO;

import java.time.OffsetDateTime;

public class DtoUnitTests {
    public static void main(String[] args) {
        int failures = 0;

        // UsersDTO
        OffsetDateTime now = OffsetDateTime.now();
        UsersDTO u = new UsersDTO(1, "Alice", "a@example.com", now);
        if (u.getId() != 1) { System.err.println("UsersDTO id mismatch"); failures++; }
        if (!"Alice".equals(u.getName())) { System.err.println("UsersDTO name mismatch"); failures++; }
        if (!"a@example.com".equals(u.getEmail())) { System.err.println("UsersDTO email mismatch"); failures++; }
        if (!now.equals(u.getLastModification())) { System.err.println("UsersDTO lastModification mismatch"); failures++; }

        // TypeObjectDTO
        TypeObjectDTO t = new TypeObjectDTO(2, "TypeA", "desc", now);
        if (t.getId() != 2) { System.err.println("TypeObjectDTO id mismatch"); failures++; }
        if (!"TypeA".equals(t.getTypeName())) { System.err.println("TypeObjectDTO typeName mismatch"); failures++; }
        if (!"desc".equals(t.getDescription())) { System.err.println("TypeObjectDTO description mismatch"); failures++; }
        if (!now.equals(t.getLastModification())) { System.err.println("TypeObjectDTO lastModification mismatch"); failures++; }

        // ObjectDTO
        ObjectDTO o = new ObjectDTO(3, "Obj", 2, 1, now);
        if (o.getId() != 3) { System.err.println("ObjectDTO id mismatch"); failures++; }
        if (!"Obj".equals(o.getName())) { System.err.println("ObjectDTO name mismatch"); failures++; }
        if (o.getTypeId() == null || o.getTypeId() != 2) { System.err.println("ObjectDTO typeId mismatch"); failures++; }
        if (o.getCreatedBy() == null || o.getCreatedBy() != 1) { System.err.println("ObjectDTO createdBy mismatch"); failures++; }
        if (!now.equals(o.getLastModification())) { System.err.println("ObjectDTO lastModification mismatch"); failures++; }

        // MaintenanceDTO
        MaintenanceDTO m = new MaintenanceDTO(4, 3, "Fix", 1, now);
        if (m.getId() != 4) { System.err.println("MaintenanceDTO id mismatch"); failures++; }
        if (m.getObjectId() != 3) { System.err.println("MaintenanceDTO objectId mismatch"); failures++; }
        if (!"Fix".equals(m.getDescription())) { System.err.println("MaintenanceDTO description mismatch"); failures++; }
        if (m.getPerformedBy() == null || m.getPerformedBy() != 1) { System.err.println("MaintenanceDTO performedBy mismatch"); failures++; }
        if (!now.equals(m.getLastModification())) { System.err.println("MaintenanceDTO lastModification mismatch"); failures++; }

        if (failures == 0) {
            System.out.println("All DTO tests passed.");
            System.exit(0);
        } else {
            System.err.println(failures + " tests failed.");
            System.exit(2);
        }
    }
}
