package Zoo_Project;

import java.time.LocalDateTime;
import java.util.Scanner;

public class VisitorsService implements Zoo_Service<Visitors> {

    Repository repo = new Repository();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void createTable() {
        repo.createVisitorsTable();
    }

    @Override
    public void ListAll() {
        repo.getList("t_visitors");
    }

    @Override
    public void addANew() {
        repo.addVisitor(getIntro());
    }

    @Override
    public void update(int id) {
        if (repo.findById(id, "t_visitors")) {
            Visitors visitor = new Visitors();
            System.out.print("Name of visitor: ");
            visitor.setFirstName(scanner.nextLine());
            System.out.print("LastName of Visitor: ");
            visitor.setLastName(scanner.nextLine());
            repo.updateVisitor(visitor, id);
        } else System.out.println("The record is not found");
    }

    @Override
    public void filter() {
        System.out.println("Enter animal name: ");
        String name = scanner.nextLine();
        repo.filter("t_visitors", name);
    }

    @Override
    public void delete(int id) {
        repo.delete(id, "t_visitors");
    }

    public Visitors getIntro() {
        Visitors visitor = new Visitors();
        System.out.print("Name of visitor: ");
        visitor.setFirstName(scanner.nextLine());
        System.out.print("LastName of Visitor: ");
        visitor.setLastName(scanner.nextLine());
        //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        visitor.setEntryTime((LocalDateTime.now()));
        return visitor;
    }
}