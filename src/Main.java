import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        
        Admin admin = new Admin("1", "Admin Name");


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
            input.nextLine(); 
            //Rest of code will be DOne By Abrar ,will be sending  pull request 

            switch (choice) {
                case 1: 
                    admin.manageUsers(); 
                    break;
                case 2: 
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
                        break; 
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

                    input.nextLine(); 
                    System.out.print("Enter pickup location: ");
                    String pickupLocation = input.nextLine();
                    System.out.print("Enter dropoff location: ");
                    String dropoffLocation = input.nextLine();

                    LocalDateTime estimatedArrival = LocalDateTime.now().plusMinutes(30); // Example time
                    double fare = 500; 

                    selectedPassenger.bookRide(pickupLocation, dropoffLocation, selectedDriver, fare, estimatedArrival);
                    System.out.println("Ride booked successfully for " + selectedPassenger.getName() + " with driver "
                            + selectedDriver.getName());

                    break;
                case 3: 
                    if (!admin.getPassengers().isEmpty()) {
                        System.out.print("Enter Passenger ID to track: ");
                        String passengerID = input.nextLine();
                        Driver driver = admin.getDrivers().get(0); 
                        driver.trackPassenger(passengerID);
                    } else {
                        System.out.println("No passengers available to track.");
                    }
                    break;
                case 4: 
                    
                    if (!admin.getDrivers().isEmpty()) {
                        System.out.print("Enter Driver ID to track: ");
                        String driverID = input.nextLine();
                        Passenger passenger = admin.getPassengers().get(0); 
                        passenger.trackDriver(driverID);
                    } else {
                        System.out.println("No drivers available to track.");
                    }
                    break;
                case 5:
                    admin.handleDisputes(); 
                    break;
                case 6:
                    admin.viewSystemStatistics(); 
                    break;
                case 7: 
                   
                    if (!admin.getPassengers().isEmpty()) {
                        System.out.print("Enter Ride ID to track: ");
                        String rideID = input.nextLine();
                        Passenger passenger = admin.getPassengers().get(0); 
                        passenger.trackRide(rideID);
                    }
                    break;
                case 8: 
                    
                    if (!admin.getPassengers().isEmpty()) {
                        Passenger passenger = admin.getPassengers().get(0);
                        if (!passenger.getRides().isEmpty()) {
                            Ride ride = passenger.getRides().get(0);
                            ride.completeRide();
                        }
                    }
                    break;
                case 9: 
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        } while (choice != 9);

        input.close();
    }
}