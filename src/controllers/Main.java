package controllers;

import interfaces.IPostgreSQLConnector;
import connections.PostgreSQLConnector;
import handlers.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/GAO_DB";
        String user = "univatesST";
        String password = "UniProject2.";

        IPostgreSQLConnector connector = new PostgreSQLConnector();

        UsersHandler userHandler = new UsersHandler(connector);
        ObjectsHandler objectsHandler = new ObjectsHandler(connector);
        TypeObjectsHandler typeHandler = new TypeObjectsHandler(connector);
        MaintenanceHandler maintenanceHandler = new MaintenanceHandler(connector);

        System.out.println("Listing users:");
        try {
            userHandler.listAll(url, user, password).forEach(u -> System.out.println(u.getId() + "\t" + u.getName() + "\t" + u.getEmail()));
        } catch (Exception e) {
            System.err.println("Error listing users: " + e.getMessage());
        }

        System.out.println("\nListing objects:");
        try {
            objectsHandler.listAll(url, user, password).forEach(o -> System.out.println(o.getId() + "\t" + o.getName()));
        } catch (Exception e) {
            System.err.println("Error listing objects: " + e.getMessage());
        }

        System.out.println("\nListing type objects:");
        try {
            typeHandler.listAll(url, user, password).forEach(t -> System.out.println(t.getId() + "\t" + t.getTypeName()));
        } catch (Exception e) {
            System.err.println("Error listing type objects: " + e.getMessage());
        }

        System.out.println("\nListing maintenance items:");
        try {
            maintenanceHandler.listAll(url, user, password).forEach(m -> System.out.println(m.getId() + "\t" + m.getDescription()));
        } catch (Exception e) {
            System.err.println("Error listing maintenance items: " + e.getMessage());
        }
    }
}




