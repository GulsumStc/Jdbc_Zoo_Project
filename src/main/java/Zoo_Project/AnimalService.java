package Zoo_Project;

import java.util.List;
import java.util.Scanner;

public class AnimalService implements Zoo_Service<Animal> {

    Repository repo = new Repository();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void createTable() {

        repo.createAnimalsTable();
    }

    @Override
    public void ListAll() {

        repo.getList("t_animals");

    }

    @Override
    public void addANew() {
        repo.addAnimal(getIntro());
    }


    @Override
    public void update(int id) {
        if (repo.findById(id, "t_animals")) {
            repo.updateAnimal(getIntro(), id);
        } else System.out.println("The record is not found");

    }

    @Override
    public void filter() {

        System.out.println("Enter animal name: ");
        String name = scanner.nextLine();
        List<Animal> animalList = repo.filter("t_animals", name);

        if (animalList.size() == 0) {
            System.out.println("The animal does not exists");
        } else {
            animalList.forEach(System.out::println);
        }
    }


    @Override
    public void delete(int id) {

        if (repo.findById(id, "t_animals")) {
            repo.delete(id, "t_animals");
        } else System.out.println("The animal is not found");

    }

    public Animal getIntro() {
        Animal animal = new Animal();
        System.out.print("Name of animal: ");
        animal.setName(scanner.nextLine());
        System.out.print("Species of animal: ");
        animal.setSpecies(scanner.nextLine());
        System.out.print("Age of animal: ");
        animal.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Gender of animal: ");
        animal.setGender(scanner.nextLine());
        System.out.print("Health Status of animal: ");
        animal.setHealth_status(scanner.nextLine());
        System.out.print("Cage Number of animal: ");
        animal.setCage_no(scanner.nextInt());
        scanner.nextLine();
        return animal;
    }
}
