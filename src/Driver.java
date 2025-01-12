import java.util.ArrayList;
import java.util.List;

public class Driver extends User {

    private double wallet; // Wallet to manage credits or funds
    private List<Ride> assignedRides;
    private String currentLocation;
    private Vehicle vehicle; // Single vehicle object

    public Driver(String id, String name, String email, String phone, String password, String currentLocation,
            Vehicle vehicle) {
        super(id, name, email, phone, password);
        this.wallet = 0; // Initial wallet balance
        this.assignedRides = new ArrayList<>();
        this.currentLocation = currentLocation;
        this.vehicle = vehicle; // Assign vehicle
    }

    // Accessors
    public double getWallet() {
        return wallet;
    }

    public List<Ride> getAssignedRides() {
        return assignedRides;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    // Mutators
    public void setAssignedRides(List<Ride> assignedRides) {
        this.assignedRides = assignedRides;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // Methods
    public void assignRide(Ride ride) {
        assignedRides.add(ride);
    }

    public void updateLocation(String currentLocation) {
        setCurrentLocation(currentLocation);
        System.out.println("Driver location updated to: " + currentLocation);
    }

    public boolean isWheelchairAccessible() {
        return vehicle != null && vehicle.isWheelchairAccessible();
    }

    public void trackPassenger(String passengerID) {
        boolean found = false;

        for (Ride ride : this.assignedRides) {
            if (ride.getPassenger().getID().equals(passengerID)) {
                found = true;
                System.out.println("Tracking Passenger...");
                System.out.println("Passenger ID: " + ride.getPassenger().getID());
                System.out.println("Passenger Name: " + ride.getPassenger().getName());
                break;
            }
        }
        if (!found) {
            System.out.println("No Passenger found with the ID: " + passengerID);
        }
    }

    // Deposit funds into the driver's wallet
    public void deposit(double amount) {
        wallet += amount;
        System.out.println("Deposited " + amount + " into driver's wallet. New balance: " + wallet);
    }

    // Withdraw funds from the driver's wallet
    public boolean withdraw(double amount) {
        if (wallet >= amount) {
            wallet -= amount;
            System.out.println("Withdrew " + amount + " from driver's wallet. New balance: " + wallet);
            return true;
        } else {
            System.out.println("Insufficient funds in driver's wallet.");
            return false;
        }
    }

    // Process ride completion and add the fare to the driver's wallet
    public void completeRide(Ride ride) {
        if (assignedRides.contains(ride) && ride.getStatus().equals("Completed")) {
            double fare = ride.getFare();
            deposit(fare);
            System.out.println("Ride completed. Fare credited: " + fare);
        } else {
            System.out.println("Ride not found or not completed yet.");
        }
    }

    // Handle cancellation of a pre-scheduled ride and refund of advance payment
    public void cancelPreBookRide(Ride ride) {
        if (assignedRides.contains(ride) && ride.isPreScheduled()) {
            double advancePayment = ride.getFare() * 0.05; // 5% advance payment
            withdraw(advancePayment); // Deduct from driver's wallet due to cancellation
            ride.getPassenger().deposit(advancePayment);

            System.out.println("Pre-scheduled ride canceled. Refund to passenger: " + advancePayment);
            assignedRides.remove(ride); // Remove the ride from assigned rides
        } else {
            System.out.println("Ride not found or it's not a pre-scheduled ride.");
        }
    }

    public void cancelRide(Ride ride) {
        if (assignedRides.contains(ride) && !ride.isPreScheduled()) {

            System.out.println("Ride with ID " + ride.getRideID() + " has been cancelled by the driver.");
        } else {
            if (ride.isPreScheduled()) {
                System.out.println("Cannot cancel pre-booked ride. Use cancelPreBookRide instead.");
            } else {
                System.out.println("Ride not found in driver's assigned rides.");
            }
        }
    }

    public void viewRideHistory() {
        System.out.println("=====================================");
        System.out.println("Ride History for Driver: " + getName());
        System.out.println("=====================================");

        // Check if there are no rides
        if (assignedRides.isEmpty()) {
            System.out.println("No rides assigned.");
        } else {
            for (Ride ride : assignedRides) {
                System.out.println("\n-------------------------------");
                System.out.println("Ride ID       : " + ride.getRideID());
                System.out.println("Passenger Name: " + ride.getPassenger().getName());
                System.out.println("Passenger ID  : " + ride.getPassenger().getID());
                System.out.println("Fare          : $" + ride.getFare());
                System.out.println("Status        : " + ride.getStatus());
                System.out.println("Pickup Location : " + ride.getPickupLocation());
                System.out.println("DropOff Location: " + ride.getDropoffLocation());
                System.out.println("Ride Date     : " + ride.getEstimatedArrivalTime());
                System.out.println("-------------------------------");
            }
        }
        System.out.println("=====================================");
    }

}
