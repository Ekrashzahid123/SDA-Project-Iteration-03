import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create Admin
        Admin admin = Admin.getInstance("1", "Admin");

        // Menu loop for testing
        int choice;
        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. Admin - Manage Users");
            System.out.println("2. Passenger - Book Normal Ride");
            System.out.println("3. Passenger - Pre-Book Ride");
            System.out.println("4. Driver - Track Passenger");
            System.out.println("5. Passenger - Track Driver");
            System.out.println("6. Admin - Handle Disputes");
            System.out.println("7. Admin - View System Statistics");
            System.out.println("8. Passenger - Track Ride");
            System.out.println("9. Complete Ride");
            System.out.println("10. Cancel Normal Ride");
            System.out.println("11. Cancel Pre-Booked Ride");
            System.out.println("12. View Ride History for a Specific Passenger");
            System.out.println("13. Exit");
            choice = input.nextInt();
            input.nextLine(); // Consume newline character after nextInt()

            switch (choice) {
                case 1: // Admin - Manage Users
                    admin.manageUsers();
                    break;

                case 2: // Book Normal Ride
                    if (admin.getPassengers().isEmpty() && admin.getWheelchairUsers().isEmpty()) {
                        System.out.println("No users available. Please add passengers or wheelchair users first.");
                        break;
                    }
                    if (admin.getDrivers().isEmpty()) {
                        System.out.println("No drivers available. Please add drivers first.");
                        break;
                    }

                    System.out.println("Select a user to book a ride:");
                    int optionIndex = 1;
                    for (Passenger passenger : admin.getPassengers()) {
                        System.out.println(optionIndex + ". Passenger: " + passenger.getName());
                        optionIndex++;
                    }
                    for (WheelchairUser wheelchairUser : admin.getWheelchairUsers()) {
                        System.out.println(optionIndex + ". Wheelchair User: " + wheelchairUser.getName());
                        optionIndex++;
                    }
                    System.out.print("Enter user number: ");
                    int userChoice = input.nextInt();
                    input.nextLine();

                    if (userChoice < 1 || userChoice >= optionIndex) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    Passenger selectedPassenger = null;
                    WheelchairUser selectedWheelchairUser = null;
                    if (userChoice <= admin.getPassengers().size()) {
                        selectedPassenger = admin.getPassengers().get(userChoice - 1);
                    } else {
                        selectedWheelchairUser = admin.getWheelchairUsers()
                                .get(userChoice - admin.getPassengers().size() - 1);
                    }

                    System.out.println("Select a driver for the ride:");
                    for (int i = 0; i < admin.getDrivers().size(); i++) {
                        System.out.println((i + 1) + ". " + admin.getDrivers().get(i).getName());
                    }
                    System.out.print("Enter driver number: ");
                    int driverChoice = input.nextInt();
                    input.nextLine();

                    if (driverChoice < 1 || driverChoice > admin.getDrivers().size()) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    Driver selectedDriver = admin.getDrivers().get(driverChoice - 1);

                    System.out.print("Enter pickup location: ");
                    String pickupLocation = input.nextLine();
                    System.out.print("Enter dropoff location: ");
                    String dropoffLocation = input.nextLine();

                    LocalDateTime estimatedArrival = LocalDateTime.now().plusMinutes(30);

                    // Let the user decide the fare
                    System.out.print("Enter fare for the ride: ");
                    double fare = input.nextDouble();
                    input.nextLine();

                    if (selectedPassenger != null) {
                        selectedPassenger.bookRide(pickupLocation, dropoffLocation, selectedDriver, fare,
                                estimatedArrival);
                    } else {
                        selectedWheelchairUser.bookRide(pickupLocation, dropoffLocation, selectedDriver, fare,
                                estimatedArrival);
                    }
                    break;

                case 3: // Pre-Book Ride
                    if (admin.getPassengers().isEmpty() && admin.getWheelchairUsers().isEmpty()) {
                        System.out.println("No users available. Please add passengers or wheelchair users first.");
                        break;
                    }
                    if (admin.getDrivers().isEmpty()) {
                        System.out.println("No drivers available. Please add drivers first.");
                        break;
                    }

                    System.out.println("Select a user to pre-book a ride:");
                    optionIndex = 1;
                    for (Passenger passenger : admin.getPassengers()) {
                        System.out.println(optionIndex + ". Passenger: " + passenger.getName());
                        optionIndex++;
                    }
                    for (WheelchairUser wheelchairUser : admin.getWheelchairUsers()) {
                        System.out.println(optionIndex + ". Wheelchair User: " + wheelchairUser.getName());
                        optionIndex++;
                    }
                    System.out.print("Enter user number: ");
                    userChoice = input.nextInt();
                    input.nextLine();

                    if (userChoice < 1 || userChoice >= optionIndex) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    selectedPassenger = null;
                    selectedWheelchairUser = null;
                    if (userChoice <= admin.getPassengers().size()) {
                        selectedPassenger = admin.getPassengers().get(userChoice - 1);
                    } else {
                        selectedWheelchairUser = admin.getWheelchairUsers()
                                .get(userChoice - admin.getPassengers().size() - 1);
                    }

                    System.out.println("Select a driver for the pre-booked ride:");
                    for (int i = 0; i < admin.getDrivers().size(); i++) {
                        System.out.println((i + 1) + ". " + admin.getDrivers().get(i).getName());
                    }
                    System.out.print("Enter driver number: ");
                    driverChoice = input.nextInt();
                    input.nextLine();

                    if (driverChoice < 1 || driverChoice > admin.getDrivers().size()) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    selectedDriver = admin.getDrivers().get(driverChoice - 1);

                    System.out.print("Enter pickup location: ");
                    pickupLocation = input.nextLine();
                    System.out.print("Enter dropoff location: ");
                    dropoffLocation = input.nextLine();

                    System.out.print("Enter the days in advance you want to book the ride: ");
                    int advanceDays = input.nextInt();

                    System.out.print("Enter fare for the pre-booked ride: ");
                    fare = input.nextDouble();
                    input.nextLine();

                    PaymentStrategyInterface paymentStrategy = new DiscountPaymentStrategy(fare);

                    if (selectedPassenger != null) {
                        selectedPassenger.preBookRide(pickupLocation, dropoffLocation, selectedDriver, fare,
                                advanceDays, paymentStrategy);
                    } else {
                        selectedWheelchairUser.preBookRide(pickupLocation, dropoffLocation, selectedDriver, fare,
                                advanceDays);
                    }
                    break;

                case 4: // Driver - Track Passenger
                    if (admin.getDrivers().isEmpty()) {
                        System.out.println("No drivers available. Please add drivers first.");
                        break;
                    }

                    System.out.println("Select a driver:");
                    int driverIndex = 1;
                    for (Driver driver : admin.getDrivers()) {
                        System.out.println(driverIndex++ + ". Driver: " + driver.getName());
                    }

                    System.out.print("Enter driver number: ");
                    int selectedDriverIndex = input.nextInt();
                    input.nextLine(); // Consume newline character

                    if (selectedDriverIndex < 1 || selectedDriverIndex > admin.getDrivers().size()) {
                        System.out.println("Invalid driver selection.");
                        break;
                    }

                    Driver selectedDriver1 = admin.getDrivers().get(selectedDriverIndex - 1);

                    if (admin.getPassengers().isEmpty() && admin.getWheelchairUsers().isEmpty()) {
                        System.out.println("No passengers or wheelchair users available to track.");
                        break;
                    }

                    System.out.println("Select a user to track:");
                    int userIndex = 1;
                    for (Passenger passenger : admin.getPassengers()) {
                        System.out.println(userIndex++ + ". Passenger: " + passenger.getName());
                    }
                    for (WheelchairUser wheelchairUser : admin.getWheelchairUsers()) {
                        System.out.println(userIndex++ + ". Wheelchair User: " + wheelchairUser.getName());
                    }

                    System.out.print("Enter user number: ");
                    int selectedUserIndex = input.nextInt();
                    input.nextLine(); // Consume newline character

                    if (selectedUserIndex < 1
                            || selectedUserIndex > admin.getPassengers().size() + admin.getWheelchairUsers().size()) {
                        System.out.println("Invalid user selection.");
                        break;
                    }

                    if (selectedUserIndex <= admin.getPassengers().size()) {
                        Passenger selectedPassenger1 = admin.getPassengers().get(selectedUserIndex - 1);
                        selectedDriver1.trackPassenger(selectedPassenger1.getID());
                        System.out.println("Driver " + selectedDriver1.getName() + " is now tracking Passenger: "
                                + selectedPassenger1.getName());
                    } else {
                        WheelchairUser selectedWheelchairUser1 = admin.getWheelchairUsers()
                                .get(selectedUserIndex - admin.getPassengers().size() - 1);
                        selectedDriver1.trackPassenger(selectedWheelchairUser1.getID());
                        System.out.println("Driver " + selectedDriver1.getName() + " is now tracking Wheelchair User: "
                                + selectedWheelchairUser1.getName());
                    }
                    break;
                case 5: // Passenger - Track Driver
                    if (admin.getPassengers().isEmpty() && admin.getWheelchairUsers().isEmpty()) {
                        System.out.println(
                                "No passengers or wheelchair users available. Please add passengers or wheelchair users first.");
                        break;
                    }

                    System.out.println("Select a passenger or wheelchair user:");
                    int passengerIndex = 1;
                    for (Passenger passenger : admin.getPassengers()) {
                        System.out.println(passengerIndex++ + ". Passenger: " + passenger.getName());
                    }
                    for (WheelchairUser wheelchairUser : admin.getWheelchairUsers()) {
                        System.out.println(passengerIndex++ + ". Wheelchair User: " + wheelchairUser.getName());
                    }

                    System.out.print("Enter user number: ");
                    int selectedUserIndex1 = input.nextInt();
                    input.nextLine(); // Consume newline character

                    if (selectedUserIndex1 < 1
                            || selectedUserIndex1 > admin.getPassengers().size() + admin.getWheelchairUsers().size()) {
                        System.out.println("Invalid user selection.");
                        break;
                    }

                    if (selectedUserIndex1 <= admin.getPassengers().size()) {
                        // Track Driver for selected Passenger
                        Passenger selectedPassenger1 = admin.getPassengers().get(selectedUserIndex1 - 1);
                        if (admin.getDrivers().isEmpty()) {
                            System.out.println("No drivers available to track.");
                            break;
                        }

                        System.out.println("Select a driver to track:");
                        int driverIndex1 = 1;
                        for (Driver driver : admin.getDrivers()) {
                            System.out.println(driverIndex1++ + ". Driver: " + driver.getName());
                        }

                        System.out.print("Enter driver number: ");
                        int selectedDriverIndex2 = input.nextInt();
                        input.nextLine(); // Consume newline character

                        if (selectedDriverIndex2 < 1 || selectedDriverIndex2 > admin.getDrivers().size()) {
                            System.out.println("Invalid driver selection.");
                            break;
                        }

                        Driver selectedDriver2 = admin.getDrivers().get(selectedDriverIndex2 - 1);
                        selectedPassenger1.trackDriver(selectedDriver2.getID());
                        System.out.println("Passenger " + selectedPassenger1.getName() + " is now tracking Driver: "
                                + selectedDriver2.getName());
                    } else {
                        // Track Driver for selected Wheelchair User
                        WheelchairUser selectedWheelchairUser1 = admin.getWheelchairUsers()
                                .get(selectedUserIndex1 - admin.getPassengers().size() - 1);
                        if (admin.getDrivers().isEmpty()) {
                            System.out.println("No drivers available to track.");
                            break;
                        }

                        System.out.println("Select a driver to track:");
                        int driverIndex2 = 1;
                        for (Driver driver : admin.getDrivers()) {
                            System.out.println(driverIndex2++ + ". Driver: " + driver.getName());
                        }

                        System.out.print("Enter driver number: ");
                        int selectedDriverIndex3 = input.nextInt();
                        input.nextLine(); // Consume newline character

                        if (selectedDriverIndex3 < 1 || selectedDriverIndex3 > admin.getDrivers().size()) {
                            System.out.println("Invalid driver selection.");
                            break;
                        }

                        Driver selectedDriver3 = admin.getDrivers().get(selectedDriverIndex3 - 1);
                        selectedWheelchairUser1.trackDriver(selectedDriver3.getID());
                        System.out.println("Wheelchair User " + selectedWheelchairUser1.getName()
                                + " is now tracking Driver: " + selectedDriver3.getName());
                    }
                    break;

                case 6: // Admin - Handle Disputes
                    System.out.println("Handling disputes...");
                    admin.handleDisputes();
                    break;

                case 7: // Admin - View System Statistics
                    System.out.println("Viewing system statistics...");
                    admin.viewSystemStatistics();
                    break;

                case 8: // Passenger - Track Ride
                    System.out.print("Enter Ride ID to track: ");
                    String rideID = input.nextLine();
                    for (Passenger passenger : admin.getPassengers()) {
                        passenger.trackRide(rideID);
                    }
                    break;

                case 9: // Complete Ride
                    if (admin.getPassengers().isEmpty() && admin.getWheelchairUsers().isEmpty()) {
                        System.out.println("No users available. Please add passengers or wheelchair users first.");
                        break;
                    }

                    System.out.println("Select a user with active rides:");
                    optionIndex = 1;
                    for (Passenger passenger : admin.getPassengers()) {
                        for (Ride ride : passenger.getRides()) {
                            if (ride.getStatus().equalsIgnoreCase("In Progress")) {
                                System.out.println(optionIndex + ". Passenger: " + passenger.getName());
                                optionIndex++;
                                break;
                            }
                        }
                    }
                    for (WheelchairUser wheelchairUser : admin.getWheelchairUsers()) {
                        for (Ride ride : wheelchairUser.getRides()) {
                            if (ride.getStatus().equalsIgnoreCase("In Progress")) {
                                System.out.println(optionIndex + ". Wheelchair User: " + wheelchairUser.getName());
                                optionIndex++;
                                break;
                            }
                        }
                    }

                    if (optionIndex == 1) {
                        System.out.println("No users with active rides.");
                        break;
                    }

                    System.out.print("Enter user number: ");
                    userChoice = input.nextInt();
                    input.nextLine();

                    if (userChoice < 1 || userChoice >= optionIndex) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    selectedPassenger = null;
                    selectedWheelchairUser = null;
                    if (userChoice <= admin.getPassengers().size()) {
                        selectedPassenger = admin.getPassengers().get(userChoice - 1);
                    } else {
                        selectedWheelchairUser = admin.getWheelchairUsers()
                                .get(userChoice - admin.getPassengers().size() - 1);
                    }

                    List<Ride> activeRides = new ArrayList<>();
                    if (selectedPassenger != null) {
                        for (Ride ride : selectedPassenger.getRides()) {
                            if (ride.getStatus().equalsIgnoreCase("In Progress")) {
                                activeRides.add(ride);
                            }
                        }
                    } else {
                        for (Ride ride : selectedWheelchairUser.getRides()) {
                            if (ride.getStatus().equalsIgnoreCase("In Progress")) {
                                activeRides.add(ride);
                            }
                        }
                    }

                    if (activeRides.isEmpty()) {
                        System.out.println("No active rides for this user.");
                        break;
                    }

                    System.out.println("Select a ride to complete:");
                    for (int i = 0; i < activeRides.size(); i++) {
                        System.out.println((i + 1) + ". Ride ID: " + activeRides.get(i).getRideID());
                    }

                    int rideChoice = input.nextInt();
                    input.nextLine();

                    if (rideChoice < 1 || rideChoice > activeRides.size()) {
                        System.out.println("Invalid ride selection.");
                        break;
                    }

                    Ride selectedRide = activeRides.get(rideChoice - 1);
                    if (selectedPassenger != null) {
                        selectedPassenger.completeRide(selectedRide);
                        System.out.println("Ride completed successfully for Passenger: " + selectedPassenger.getName());
                    } else {
                        selectedWheelchairUser.completeRide(selectedRide);
                        System.out.println(
                                "Ride completed successfully for Wheelchair User: " + selectedWheelchairUser.getName());
                    }
                    break;
                case 10: // Cancel Normal Ride
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Select a user to cancel their ride:");
                    List<Passenger> allPassengers = admin.getPassengers();
                    List<WheelchairUser> allWheelchairUsers = admin.getWheelchairUsers();

                    int index = 1;
                    for (Passenger passenger : allPassengers) {
                        System.out.println(index++ + ". Passenger: " + passenger.getName());
                    }
                    for (WheelchairUser wheelchairUser : allWheelchairUsers) {
                        System.out.println(index++ + ". Wheelchair User: " + wheelchairUser.getName());
                    }

                    System.out.print("Enter user number: ");
                    int userChoice1 = scanner.nextInt();
                    scanner.nextLine();

                    if (userChoice1 < 1 || userChoice1 > allPassengers.size() + allWheelchairUsers.size()) {
                        System.out.println("Invalid user number.");
                        break;
                    }

                    List<Ride> ridesToCancel = new ArrayList<>();
                    if (userChoice1 <= allPassengers.size()) {
                        Passenger passenger = allPassengers.get(userChoice1 - 1);
                        for (Ride ride : passenger.getRides()) {
                            if (ride.getStatus().equals("In Progress")) {
                                ridesToCancel.add(ride);
                            }
                        }
                        if (ridesToCancel.isEmpty()) {
                            System.out.println("No rides to cancel for this passenger.");
                            break;
                        }
                        System.out.println("Select a ride to cancel:");
                        for (int i = 0; i < ridesToCancel.size(); i++) {
                            System.out.println((i + 1) + ". Ride ID: " + ridesToCancel.get(i).getRideID());
                        }

                        int rideChoice1 = scanner.nextInt();
                        scanner.nextLine();

                        if (rideChoice1 < 1 || rideChoice1 > ridesToCancel.size()) {
                            System.out.println("Invalid ride selection.");
                            break;
                        }

                        passenger.cancelRide(ridesToCancel.get(rideChoice1 - 1));
                        System.out.println("Ride canceled successfully for Passenger: " + passenger.getName());
                    } else {
                        WheelchairUser wheelchairUser = allWheelchairUsers.get(userChoice1 - allPassengers.size() - 1);
                        for (Ride ride : wheelchairUser.getRides()) {
                            if (ride.getStatus().equals("In Progress")) {
                                ridesToCancel.add(ride);
                            }
                        }
                        if (ridesToCancel.isEmpty()) {
                            System.out.println("No rides to cancel for this wheelchair user.");
                            break;
                        }
                        System.out.println("Select a ride to cancel:");
                        for (int i = 0; i < ridesToCancel.size(); i++) {
                            System.out.println((i + 1) + ". Ride ID: " + ridesToCancel.get(i).getRideID());
                        }

                        int rideChoice2 = scanner.nextInt();
                        scanner.nextLine();

                        if (rideChoice2 < 1 || rideChoice2 > ridesToCancel.size()) {
                            System.out.println("Invalid ride selection.");
                            break;
                        }

                        wheelchairUser.cancelRide(ridesToCancel.get(rideChoice2 - 1));
                        System.out
                                .println("Ride canceled successfully for Wheelchair User: " + wheelchairUser.getName());
                    }
                    break;
                case 11: // Cancel Pre-Booked Ride
                    System.out.println("Select a user to cancel their pre-booked ride:");
                    List<Passenger> allPassengers1 = admin.getPassengers();
                    List<WheelchairUser> allWheelchairUsers1 = admin.getWheelchairUsers();
                    index = 1;
                    for (Passenger passenger : allPassengers1) {
                        System.out.println(index++ + ". Passenger: " + passenger.getName());
                    }
                    for (WheelchairUser wheelchairUser : allWheelchairUsers1) {
                        System.out.println(index++ + ". Wheelchair User: " + wheelchairUser.getName());
                    }

                    System.out.print("Enter user number: ");
                    int userChoice3 = input.nextInt();
                    input.nextLine();

                    if (userChoice3 < 1 || userChoice3 > allPassengers1.size() + allWheelchairUsers1.size()) {
                        System.out.println("Invalid user number.");
                        break;
                    }

                    List<Ride> scheduledRides = new ArrayList<>();
                    if (userChoice3 <= allPassengers1.size()) {
                        Passenger passenger = allPassengers1.get(userChoice3 - 1);
                        for (Ride ride : passenger.getRides()) {
                            if (ride.getStatus().equals("Scheduled")) { // Pre-booked rides are "Scheduled"
                                scheduledRides.add(ride);
                            }
                        }
                        if (scheduledRides.isEmpty()) {
                            System.out.println("No pre-booked rides for this passenger.");
                            break;
                        }

                        System.out.println("Select a pre-booked ride to cancel:");
                        for (int i = 0; i < scheduledRides.size(); i++) {
                            System.out.println((i + 1) + ". Ride ID: " + scheduledRides.get(i).getRideID());
                        }

                        int rideChoice3 = input.nextInt();
                        input.nextLine();

                        if (rideChoice3 < 1 || rideChoice3 > scheduledRides.size()) {
                            System.out.println("Invalid ride selection.");
                            break;
                        }

                        passenger.cancelPreBookRide(scheduledRides.get(rideChoice3 - 1));
                        System.out
                                .println("Pre-booked ride canceled successfully for Passenger: " + passenger.getName());
                    } else {
                        WheelchairUser wheelchairUser = allWheelchairUsers1
                                .get(userChoice3 - allPassengers1.size() - 1);
                        for (Ride ride : wheelchairUser.getRides()) {
                            if (ride.getStatus().equals("Scheduled")) { // Pre-booked rides are "Scheduled"
                                scheduledRides.add(ride);
                            }
                        }
                        if (scheduledRides.isEmpty()) {
                            System.out.println("No pre-booked rides for this wheelchair user.");
                            break;
                        }

                        System.out.println("Select a pre-booked ride to cancel:");
                        for (int i = 0; i < scheduledRides.size(); i++) {
                            System.out.println((i + 1) + ". Ride ID: " + scheduledRides.get(i).getRideID());
                        }

                        int rideChoice4 = input.nextInt();
                        input.nextLine();

                        if (rideChoice4 < 1 || rideChoice4 > scheduledRides.size()) {
                            System.out.println("Invalid ride selection.");
                            break;
                        }

                        wheelchairUser.cancelPreBookRide(scheduledRides.get(rideChoice4 - 1));
                        System.out.println("Pre-booked ride canceled successfully for Wheelchair User: "
                                + wheelchairUser.getName());
                    }
                    break;

                case 12: // View Ride History for a Specific User
                    System.out.println("Select user type to view ride history:");
                    System.out.println("1. Passenger");
                    System.out.println("2. Driver");
                    System.out.println("3. Wheelchair User");
                    System.out.print("Enter your choice: ");
                    int userTypeChoice = input.nextInt();
                    input.nextLine();

                    List<?> usersToDisplay = null; // This will hold the list of users to display

                    switch (userTypeChoice) {
                        case 1: // Passengers
                            if (admin.getPassengers().isEmpty()) {
                                System.out.println("No passengers available.");
                                break;
                            }
                            usersToDisplay = admin.getPassengers();
                            break;

                        case 2: // Drivers
                            if (admin.getDrivers().isEmpty()) {
                                System.out.println("No drivers available.");
                                break;
                            }
                            usersToDisplay = admin.getDrivers();
                            break;

                        case 3: // Wheelchair Users
                            if (admin.getWheelchairUsers().isEmpty()) {
                                System.out.println("No wheelchair users available.");
                                break;
                            }
                            usersToDisplay = admin.getWheelchairUsers();
                            break;

                        default:
                            System.out.println("Invalid choice. Returning to the main menu.");
                            break;
                    }

                    // Check if usersToDisplay is null or empty, then exit
                    if (usersToDisplay == null || usersToDisplay.isEmpty()) {
                        break; // Return to the main menu
                    }

                    // Display the users of the selected type
                    for (int i = 0; i < usersToDisplay.size(); i++) {
                        Object user = usersToDisplay.get(i);
                        String name = ""; // Determine the name based on the user type

                        if (user instanceof Passenger) {
                            name = ((Passenger) user).getName();
                        } else if (user instanceof Driver) {
                            name = ((Driver) user).getName();
                        } else if (user instanceof WheelchairUser) {
                            name = ((WheelchairUser) user).getName();
                        }

                        System.out.println((i + 1) + ". " + name);
                    }

                    System.out.print("Enter the selected user number: ");
                    int selectedUserChoice = input.nextInt();
                    input.nextLine();

                    if (selectedUserChoice < 1 || selectedUserChoice > usersToDisplay.size()) {
                        System.out.println("Invalid selection. Returning to the main menu.");
                        break;
                    }

                    // Display the ride history for the selected user
                    Object selectedUser = usersToDisplay.get(selectedUserChoice - 1);
                    if (selectedUser instanceof Passenger) {
                        ((Passenger) selectedUser).viewRideHistory();
                    } else if (selectedUser instanceof Driver) {
                        ((Driver) selectedUser).viewRideHistory();
                    } else if (selectedUser instanceof WheelchairUser) {
                        ((WheelchairUser) selectedUser).viewRideHistory();
                    }

                    break;

                case 13: // Exit
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (choice != 13);

        input.close();
    }
}
