import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    
    private int id;
    private String name;
    private List<Passenger> passengers;
    private List<Driver> drivers;

    public Admin(String id, String name) {
        this.id = id;
        this.name = name;
        this.passengers = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    //Accessors
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Mutators
    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Methods
    public void manageUsers() {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. View All Users");
        System.out.println("Enter choice: ");

        choice = input.nextInt();

        switch (choice) {
            case 1:
                {
                    System.out.println("Which user would like to add?");
                    System.out.println("1. Passenger");
                    System.out.println("2. Driver");
                    System.out.println("Enter Choice");

                    choice = input.nextInt();

                    switch (choice) {
                        case 1:
                            {
                                System.out.print("Enter user ID: ");
                                String id = input.nextLine();
                                System.out.print("Enter name: ");
                                String name = input.nextLine();
                                System.out.print("Enter email: ");
                                String email = input.nextLine();
                                System.out.print("Enter phone: ");
                                String phone = input.nextLine();
                                System.out.print("Enter password: ");
                                String password = input.nextLine();
                
                                Passenger newPassenger = new Passenger(id, name, email, phone, password);
                                Passenger.add(newPassenger);
                                System.out.println("Passenger added successfully!");
                            }
                            break;
                        case 2:
                            {
                                System.out.print("Enter user ID: ");
                                String id = input.nextLine();
                                System.out.print("Enter name: ");
                                String name = input.nextLine();
                                System.out.print("Enter email: ");
                                String email = input.nextLine();
                                System.out.print("Enter phone: ");
                                String phone = input.nextLine();
                                System.out.print("Enter password: ");
                                String password = input.nextLine();
                
                                Driver newDriver = new Driver(id, name, email, phone, password);
                                Driver.add(newDriver);
                                System.out.println("Driver added successfully!");
                            }
                        default:
                        {
                            System.out.println("Invalid choice. Please choose Passenger or Driver.");
                        }
                            break;
                    }
                }
                break;
            case 2:
                {
                    System.out.println("Which user would like to remove?");
                    System.out.println("1. Passenger");
                    System.out.println("2. Driver");
                    System.out.println("Enter Choice");

                    choice = input.nextInt();
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        
    }
}
