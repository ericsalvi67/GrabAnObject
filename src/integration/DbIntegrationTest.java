package integration;

import connections.PostgreSQLConnector;
import infrastructure.IPostgreSQLConnector;
import aggregates.Users.UsersRepository;
import aggregates.Users.UsersDTO;
import aggregates.TypeObjects.TypeObjectsRepository;
import aggregates.Objects.ObjectsRepository;
import aggregates.Maintenance.MaintenanceRepository;

import java.util.List;

public class DbIntegrationTest {
    public static void main(String[] args) throws Exception {
        String url = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/yourdb");
        String user = System.getenv().getOrDefault("DB_USER", "yourusername");
        String pass = System.getenv().getOrDefault("DB_PASSWORD", "yourpassword");

        IPostgreSQLConnector connector = new PostgreSQLConnector();

        UsersRepository usersRepo = new UsersRepository(connector);
        TypeObjectsRepository typeRepo = new TypeObjectsRepository(connector);
        ObjectsRepository objRepo = new ObjectsRepository(connector);
        MaintenanceRepository maintRepo = new MaintenanceRepository(connector);

        try {
            usersRepo.open(url, user, pass);
            typeRepo.open(url, user, pass);
            objRepo.open(url, user, pass);
            maintRepo.open(url, user, pass);

            System.out.println("Inserting sample type...");
            int typeId = typeRepo.insert("SampleType", "Sample description");
            System.out.println("Type id: " + typeId);

            System.out.println("Inserting sample user...");
            int userId = usersRepo.insert("Integration User", "int@example.com");
            System.out.println("User id: " + userId);

            System.out.println("Inserting sample object...");
            int objId = objRepo.insert("Sample Object", typeId, userId);
            System.out.println("Object id: " + objId);

            System.out.println("Inserting maintenance note...");
            int mId = maintRepo.insert(objId, "Initial check", userId);
            System.out.println("Maintenance id: " + mId);

            System.out.println("Listing users:");
            List<UsersDTO> users = usersRepo.getAllUsers();
            users.forEach(u -> System.out.println(u.getId() + "\t" + u.getName() + "\t" + u.getEmail() + "\t" + u.getLastModification()));

            System.out.println("Listing objects by type:");
            objRepo.getObjectsByType(typeId).forEach(o -> System.out.println(o.getId() + "\t" + o.getName() + "\t" + o.getTypeId()));

            System.out.println("Listing maintenance for object:");
            maintRepo.getMaintenanceForObject(objId).forEach(m -> System.out.println(m.getId() + "\t" + m.getDescription() + "\t" + m.getPerformedBy()));

        } finally {
            try { usersRepo.close(); } catch (Exception ignored) {}
            try { typeRepo.close(); } catch (Exception ignored) {}
            try { objRepo.close(); } catch (Exception ignored) {}
            try { maintRepo.close(); } catch (Exception ignored) {}
        }
    }
}
