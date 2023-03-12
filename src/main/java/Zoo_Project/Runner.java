package Zoo_Project;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        start();

    }

    public static void start() {

        AnimalService as = new AnimalService();
        as.createTable();
        VisitorsService vs = new VisitorsService();
        vs.createTable();

        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("*********** Welcome To Zing Zoo ************");
        do {
            System.out.println("========================");
            System.out.println("1- Animals");
            System.out.println("2- Visitors");
            System.out.println("0- Exit");
            System.out.println("========================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    menu(as);
                    break;
                case 2:
                    menu(vs);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);


    }

    public static void menu(Zoo_Service zs) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========================");
            System.out.println("1- List all ");
            System.out.println("2- Add a new ");
            System.out.println("3- Update ");
            System.out.println("4- Find ");
            System.out.println("5- Delete ");
            System.out.println("0- Go back ");
            System.out.println("========================");
            System.out.print("Enter your choice: ");
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        }catch (Exception e){
            System.out.println("Try again...");
            choice = -1;
            scanner.nextLine();
        }
            int id;
            switch (choice) {
                case 1:
                    zs.ListAll();
                    break;
                case 2:
                    zs.addANew();
                    break;
                case 3:
                    id = getId(scanner);
                    zs.update(id);
                    break;
                case 4:
                    zs.filter();
                    break;
                case 5:
                    id = getId(scanner);
                    zs.delete(id);
                    break;
                case 0:
                    System.out.println("Going back to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private static int getId(Scanner scan) {

        System.out.print("Enter Id: ");
        int id;
        while (true) {
            try {
                id = scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Error ! " + e.getMessage());
                System.out.println("Enter Id number: ");
                scan.nextLine();// dummy
            }
        }
        return id;
    }
}






