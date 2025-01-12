import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create Admin instance (Singleton)
        Admin admin = Admin.getInstance("1", "Admin");

        // Lists to store users and drivers
        List<Passenger> passengers = new ArrayList<>();
        List<Driver> drivers = new ArrayList<>();

        // Pre-existing vehicle options
        Vehicle vehicle1 = new Vehicle("V123", "Toyota", "Camry", "5678", true); // Wheelchair accessible
        Vehicle vehicle2 = new Vehicle("V124", "Ford", "Focus", "1234", false); // Not wheelchair accessible

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage Users");
            System.out.println("2. Book Ride");
            System.out.println("3. View Ride History");
            System.out.println("4. Complete Ride");
            System.out.println("5. Cancel Ride");
            System.out.println("6. Deposit Funds");
            System.out.println("7. Withdraw Funds");
            System.out.println("8. Track Passenger");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Manage Users
                    System.out.println("\nUser Management:");
                    System.out.println("1. Add Passenger");
                    System.out.println("2. Add Driver");
                    System.out.println("3. View All Users");
                    System.out.print("Enter your choice: ");
                    int userChoice = input.nextInt();
                    input.nextLine(); // Consume newline

                    switch (userChoice) {
                        case 1: // Add Passenger
                            System.out.print("Enter Passenger Name: ");
                            String passengerName = input.nextLine();
                            System.out.print("Enter Passenger Email: ");
                            String passengerEmail = input.nextLine();
                            System.out.print("Enter Passenger Phone: ");
                            String passengerPhone = input.nextLine();
                            System.out.print("Is this passenger a wheelchair user? (yes/no): ");
                            String wheelchairInput = input.nextLine();
                            Passenger passenger = wheelchairInput.equalsIgnoreCase("yes")
                                    ? new WheelchairUser("P" + (passengers.size() + 1), passengerName, passengerEmail, passengerPhone, "password")
                                    : new Passenger("P" + (passengers.size() + 1), passengerName, passengerEmail, passengerPhone, "password");
                            passengers.add(passenger);
                            System.out.println("Passenger added successfully.");
                            break;

                        case 2: // Add Driver
                            System.out.print("Enter Driver Name: ");
                            String driverName = input.nextLine();
                            System.out.print("Enter Driver Email: ");
                            String driverEmail = input.nextLine();
                            System.out.print("Enter Driver Phone: ");
                            String driverPhone = input.nextLine();
                            System.out.print("Is the vehicle wheelchair accessible? (yes/no): ");
                            String vehicleAccessibility = input.nextLine();
                            Vehicle vehicle = vehicleAccessibility.equalsIgnoreCase("yes") ? vehicle1 : vehicle2;
                            Driver driver = new Driver("D" + (drivers.size() + 1), driverName, driverEmail, driverPhone, "password", "Location", vehicle);
                            drivers.add(driver);
                            System.out.println("Driver added successfully.");
                            break;

                        case 3: // View All Users
                            System.out.println("\nAll Passengers:");
                            for (Passenger p : passengers) {
                                System.out.println("ID: " + p.getID() + ", Name: " + p.getName());
                            }
                            System.out.println("\nAll Drivers:");
                            for (Driver d : drivers) {
                                System.out.println("ID: " + d.getID() + ", Name: " + d.getName() + ", Vehicle: " + d.getVehicle().getMake());
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Returning to main menu.");
                    }
                    break;

                case 2: // Book Ride
                    System.out.println("\nBooking a Ride:");
                    if (passengers.isEmpty() || drivers.isEmpty()) {
                        System.out.println("No passengers or drivers available. Please add users first.");
                        break;
                    }
                    System.out.println("Available Passengers:");
                    for (int i = 0; i < passengers.size(); i++) {
                        System.out.println((i + 1) + ". " + passengers.get(i).getName());
                    }
                    System.out.print("Select a passenger: ");
                    int passengerIndex = input.nextInt() - 1;
                    if (passengerIndex < 0 || passengerIndex >= passengers.size()) {
                        System.out.println("Invalid passenger selected.");
                        break;
                    }
                    Passenger selectedPassenger = passengers.get(passengerIndex);

                    System.out.println("Available Drivers:");
                    for (int i = 0; i < drivers.size(); i++) {
                        System.out.println((i + 1) + ". " + drivers.get(i).getName());
                    }
                    System.out.print("Select a driver: ");
                    int driverIndex = input.nextInt() - 1;
                    if (driverIndex < 0 || driverIndex >= drivers.size()) {
                        System.out.println("Invalid driver selected.");
                        break;
                    }
                    Driver selectedDriver = drivers.get(driverIndex);

                    System.out.print("Enter Pickup Location: ");
                    input.nextLine(); // Consume newline
                    String pickupLocation = input.nextLine();
                    System.out.print("Enter Dropoff Location: ");
                    String dropoffLocation = input.nextLine();
                    System.out.print("Enter Fare: ");
                    double fare = input.nextDouble();

                    selectedPassenger.bookRide(pickupLocation, dropoffLocation, selectedDriver, fare, LocalDateTime.now().plusMinutes(30));
                    break;

                case 3: // View Ride History
                    System.out.println("\nViewing Ride History:");
                    for (Driver d : drivers) {
                        d.viewRideHistory();
                    }
                    break;

                case 4: // Complete a Ride
                    System.out.println("\nCompleting a ride:");
                    System.out.print("Enter Driver ID: ");
                    String driverID = input.nextLine();
                    for (Driver d : drivers) {
                        if (d.getID().equals(driverID)) {
                            List<Ride> rideHistory = d.getAssignedRides(); // Assuming getRideHistory() is implemented
                            if (rideHistory.isEmpty()) {
                                System.out.println("No rides available to complete.");
                                break;
                            }
                            System.out.println("Select a ride to complete:");
                            for (int i = 0; i < rideHistory.size(); i++) {
                                System.out.println((i + 1) + ". " + rideHistory.get(i));
                            }
                            int rideIndex = input.nextInt() - 1;
                            if (rideIndex < 0 || rideIndex >= rideHistory.size()) {
                                System.out.println("Invalid ride selection.");
                            } else {
                                d.completeRide(rideHistory.get(rideIndex));
                                System.out.println("Ride completed successfully.");
                            }
                            break;
                        }
                    }                    
                    break;

                case 5: // Cancel a Ride
                    System.out.println("\nCanceling a ride:");
                    System.out.print("Enter Passenger ID: ");
                    String passengerID = input.nextLine();
                    for (Passenger p : passengers) {
                        if (p.getID().equals(passengerID)) {
                            List<Ride> rideHistory = p.getRides(); // Assuming getRideHistory() is implemented
                            if (rideHistory.isEmpty()) {
                                System.out.println("No rides available to cancel.");
                                break;
                            }
                            System.out.println("Select a ride to cancel:");
                            for (int i = 0; i < rideHistory.size(); i++) {
                                System.out.println((i + 1) + ". " + rideHistory.get(i));
                            }
                            int rideIndex = input.nextInt() - 1;
                            if (rideIndex < 0 || rideIndex >= rideHistory.size()) {
                                System.out.println("Invalid ride selection.");
                            } else {
                                p.cancelRide(rideHistory.get(rideIndex));
                                System.out.println("Ride canceled successfully.");
                            }
                            break;
                        }
                    }
                    
                    break;

                case 6: // Deposit Funds
                    System.out.println("Depositing funds to a driver's wallet.");
                    System.out.print("Enter Driver ID: ");
                    driverID = input.nextLine();
                    System.out.print("Enter Amount: ");
                    double depositAmount = input.nextDouble();
                    for (Driver d : drivers) {
                        if (d.getID().equals(driverID)) {
                            d.deposit(depositAmount);
                            break;
                        }
                    }
                    break;

                case 7: // Withdraw Funds
                    System.out.println("Withdrawing funds from a driver's wallet.");
                    System.out.print("Enter Driver ID: ");
                    driverID = input.nextLine();
                    System.out.print("Enter Amount: ");
                    double withdrawAmount = input.nextDouble();
                    for (Driver d : drivers) {
                        if (d.getID().equals(driverID)) {
                            d.withdraw(withdrawAmount);
                            break;
                        }
                    }
                    break;

                case 8: // Track a Passenger
                    System.out.println("Tracking a passenger.");
                    System.out.print("Enter Driver ID: ");
                    driverID = input.nextLine();
                    System.out.print("Enter Passenger ID: ");
                    passengerID = input.nextLine();
                    for (Driver d : drivers) {
                        if (d.getID().equals(driverID)) {
                            d.trackPassenger(passengerID);
                            break;
                        }
                    }
                    break;

                case 9: // Exit
                    System.out.println("Exiting...");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}