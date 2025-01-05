import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {

    private String id;
    private String name;
    private List<Passenger> passengers;
    private List<Driver> drivers;

    public Admin(String id, String name) {
        this.id = id;
        this.name = name;
        this.passengers = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    // Accessors
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }
    
    // Mutators
    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods
    public void manageUsers() {
        Scanner input = new Scanner(System.in);
        int choice;
    
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. View All Users");
        System.out.print("Enter choice: ");
        
        choice = input.nextInt();
        input.nextLine();
    
        switch (choice) {
            case 1: 
                {
                    System.out.println("Which user would like to add?");
                    System.out.println("1. Passenger");
                    System.out.println("2. Driver");
                    System.out.print("Enter Choice: ");
                    choice = input.nextInt();
                    input.nextLine();
    
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
                                passengers.add(newPassenger);
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
    
                                Driver newDriver = new Driver(id, name, email, phone, password, "");
                                drivers.add(newDriver);
                                System.out.println("Driver added successfully!");
                            }
                            break;
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
                    System.out.print("Enter Choice: ");
                    choice = input.nextInt();
                    input.nextLine();
    
                    switch (choice) {
                        case 1: 
                            {
                                System.out.print("Enter Passenger ID to remove: ");
                                String id = input.nextLine();
                                boolean removed = passengers.removeIf(p -> p.getID().equals(id));
    
                                if (removed) {
                                    System.out.println("Passenger removed successfully!");
                                } else {
                                    System.out.println("Passenger not found.");
                                }
                            }
                            break;
                        case 2: 
                            {
                                System.out.print("Enter Driver ID to remove: ");
                                String id = input.nextLine();
                                boolean removed = drivers.removeIf(d -> d.getID().equals(id));
    
                                if (removed) {
                                    System.out.println("Driver removed successfully!");
                                } else {
                                    System.out.println("Driver not found.");
                                }
                            }
                            break;
                        default: 
                            {
                                System.out.println("Invalid choice. Please choose Passenger or Driver.");
                            }
                            break;
                    }
                }
                break;
            case 3: 
                {
                    System.out.println("Passengers:");
                    for (Passenger passenger : passengers) {
                        System.out.println("ID: " + passenger.getID() + ", Name: " + passenger.getName() +
                                ", Email: " + passenger.getEmail() + ", Phone: " + passenger.getPhone());
                    }
                    
                    System.out.println("Drivers:");
                    for (Driver driver : drivers) {
                        System.out.println("ID: " + driver.getID() + ", Name: " + driver.getName() +
                                ", Email: " + driver.getEmail() + ", Phone: " + driver.getPhone());
                    }
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }    
    }    

    public void handleDisputes() {
        System.out.println("Admin: " + getName() + "is handling disputes...");
    }

    public void viewSystemStatistics() {
        System.out.println("Admin: " + getName() + "is viewing system statistics...");
    }
}
