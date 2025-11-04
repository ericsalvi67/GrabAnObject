package controllers;

import java.util.Scanner;

public class MainController {

    public static void main(String[] args) {
        new MainController().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            IO.println("Sistema de empréstimos de objetos");
            IO.println("");
            IO.println("1. Incluir");
            IO.println("2. Consultar");
            IO.println("3. Alterar");
            IO.println("4. Excluir");
            IO.println("5. Realizar empréstimo");
            IO.println("6. Relatórios");
            IO.println("0. Sair");
            IO.print("\nEscolha a sua opção: ");

            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    incluirMenu(sc);
                    break;
                case "2":
                    consultarMenu(sc);
                    break;
                case "3":
                    break;
                case "4":
                    deletaMenu(sc);
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    IO.println("Opção ainda não implementada.");
            }
            IO.println("");
        }
        sc.close();
    }

    private void incluirMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            IO.println("Incluir");
            IO.println("");
            IO.println("1. Pessoas");
            IO.println("2. Tipos de objetos");
            IO.println("3. Objetos");
            IO.println("4. Manutenção");
            IO.println("0. Voltar");
            IO.print("\nEscolha a sua opção: ");

            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    new UsersController().register();
                    break;
                case "2":
                    new TypeObjectsController().register();
                    break;
                case "3":
                    new ObjectsController().register();
                    break;
                case "4":
                    new MaintenanceController().register();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    IO.println("Opção inválida.");
            }
            IO.println("");
        }
    }

    private void consultarMenu(Scanner sc) {
            boolean back = false;
            while (!back) {
                IO.println("Consultar");
                IO.println("");
                IO.println("1. Pessoas");
                IO.println("2. Tipos de objetos");
                IO.println("3. Objetos");
                IO.println("4. Manutenção");
                IO.println("0. Voltar");
                IO.print("\nEscolha a sua opção: ");

                String opt = sc.nextLine();
                switch (opt) {
                    case "1":
                        new UsersController().search();
                        break;
                    case "2":
                        new TypeObjectsController().search();
                        break;
                    case "3":
                        new ObjectsController().search();
                        break;
                    case "4":
                        new MaintenanceController().search();
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        IO.println("Opção inválida.");
                }
                IO.println("");
            }
    }

    private void deletaMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            IO.println("Excluir");
            IO.println("");
            IO.println("1. Pessoas");
            IO.println("2. Tipos de objetos");
            IO.println("3. Objetos");
            IO.println("4. Manutenção");
            IO.println("0. Voltar");
            IO.print("\nEscolha a sua opção: ");

            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    new UsersController().delete();
                    break;
                case "2":
                    new TypeObjectsController().delete();
                    break;
                case "3":
                    new ObjectsController().delete();
                    break;
                case "4":
                    new MaintenanceController().delete();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    IO.println("Opção inválida.");
            }
            IO.println("");
        }
    }

}
