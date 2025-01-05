import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create Admin
        Admin admin = new Admin("1", "Admin Name");

        // Menu loop for testing
        int choice;
        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. Admin - Manage Users");
            System.out.println("2. Passenger - Book Ride");
            System.out.println("3. Driver - Track Passenger");
            System.out.println("4. Passenger - Track Driver");
            System.out.println("5. Admin - Handle Disputes");
            System.out.println("6. Admin - View System Statistics");
            System.out.println("7. Passenger - Track Ride");
            System.out.println("8. Complete Ride");
            System.out.println("9. Exit");
            choice = input.nextInt();
            input.nextLine(); // Consume newline character after nextInt()

            switch (choice) {
                case 1: // Admin - Manage Users
                    admin.manageUsers(); // This will allow the admin to add passengers/drivers dynamically
                    break;
                case 2: // Passenger - Book Ride
                    System.out.println("Select a passenger to book a ride:");
                    for (int i = 0; i < admin.getPassengers().size(); i++) {
                        System.out.println((i + 1) + ". " + admin.getPassengers().get(i).getName());
                    }
                    System.out.print("Enter passenger number: ");
                    int passengerChoice = input.nextInt();

                    if (passengerChoice < 1 || passengerChoice > admin.getPassengers().size()) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    Passenger selectedPassenger = admin.getPassengers().get(passengerChoice - 1);

                    // Check for ongoing ride immediately after selecting the passenger
                    boolean hasOngoingRide = false;
                    for (Ride ride : selectedPassenger.getRides()) {
                        if (ride.getStatus().equalsIgnoreCase("In Progress")) {
                            hasOngoingRide = true;
                            System.out.println("Passenger " + selectedPassenger.getName()
                                    + " cannot book more rides. Current ride is in progress.");
                            break;
                        }
                    }

                    if (hasOngoingRide == true) {
                        break; // Exit the case since booking cannot proceed
                    }

                    System.out.println("Select a driver for the ride:");
                    for (int i = 0; i < admin.getDrivers().size(); i++) {
                        System.out.println((i + 1) + ". " + admin.getDrivers().get(i).getName());
                    }
                    System.out.print("Enter driver number: ");
                    int driverChoice = input.nextInt();

                    if (driverChoice < 1 || driverChoice > admin.getDrivers().size()) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    Driver selectedDriver = admin.getDrivers().get(driverChoice - 1);

                    input.nextLine(); // Consume newline
                    System.out.print("Enter pickup location: ");
                    String pickupLocation = input.nextLine();
                    System.out.print("Enter dropoff location: ");
                    String dropoffLocation = input.nextLine();

                    LocalDateTime estimatedArrival = LocalDateTime.now().plusMinutes(30); // Example time
                    double fare = 500; // Example fare calculation

                    selectedPassenger.bookRide(pickupLocation, dropoffLocation, selectedDriver, fare, estimatedArrival);
                    System.out.println("Ride booked successfully for " + selectedPassenger.getName() + " with driver "
                            + selectedDriver.getName());

                    break;
                case 3: // Driver - Track Passenger
                    // Driver tracks passenger by ID
                    if (!admin.getPassengers().isEmpty()) {
                        System.out.print("Enter Passenger ID to track: ");
                        String passengerID = input.nextLine();
                        Driver driver = admin.getDrivers().get(0); // Example driver tracking the passenger
                        driver.trackPassenger(passengerID);
                    } else {
                        System.out.println("No passengers available to track.");
                    }
                    break;
                case 4: // Passenger - Track Driver
                    // Passenger tracks driver by ID
                    if (!admin.getDrivers().isEmpty()) {
                        System.out.print("Enter Driver ID to track: ");
                        String driverID = input.nextLine();
                        Passenger passenger = admin.getPassengers().get(0); // Example passenger tracking driver
                        passenger.trackDriver(driverID);
                    } else {
                        System.out.println("No drivers available to track.");
                    }
                    break;
                case 5: // Admin - Handle Disputes
                    admin.handleDisputes(); // Admin handling disputes
                    break;
                case 6: // Admin - View System Statistics
                    admin.viewSystemStatistics(); // Admin viewing system stats
                    break;
                case 7: // Passenger - Track Ride
                    // Passenger tracks ride by ID
                    if (!admin.getPassengers().isEmpty()) {
                        System.out.print("Enter Ride ID to track: ");
                        String rideID = input.nextLine();
                        Passenger passenger = admin.getPassengers().get(0); // Example passenger tracking ride
                        passenger.trackRide(rideID);
                    }
                    break;
                case 8: // Complete Ride
                    // Example ride completion
                    if (!admin.getPassengers().isEmpty()) {
                        Passenger passenger = admin.getPassengers().get(0);
                        if (!passenger.getRides().isEmpty()) {
                            Ride ride = passenger.getRides().get(0);
                            ride.completeRide();
                        }
                    }
                    break;
                case 9: // Exit
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        } while (choice != 9);

        input.close();
    }
}