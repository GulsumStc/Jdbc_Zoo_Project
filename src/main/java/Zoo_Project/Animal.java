package Zoo_Project;

public class Animal {


   private int id;
   private String name ;
   private String species;
   private int age;
   private String gender;
   private String health_status;
   private int cage_no;

   public Animal(){

   }

    public Animal(String name, String species, int age, String gender, String health_status, int cage_no) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.gender = gender;
        this.health_status = health_status;
        this.cage_no = cage_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHealth_status() {
        return health_status;
    }

    public void setHealth_status(String health_status) {
        this.health_status = health_status;
    }

    public int getCage_no() {
        return cage_no;
    }

    public void setCage_no(int cage_no) {
        this.cage_no = cage_no;
    }


    @Override
    public String toString() {
        return
                "id=" + id +
                ", name=" + name  +
                ", species=" + species  +
                ", age=" + age +
                ", gender=" + gender  +
                ", health_status=" + health_status  +
                ", cage_no=" + cage_no ;
    }
}
