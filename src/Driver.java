import java.util.ArrayList;
import java.util.List;

public class Driver extends User {

    private List<Ride> assignedRides;
    String currentLocation;

    public Driver(String id, String name, String email, String phone, String password, String currentLocation) {
        super(id, name, email, phone, password);
        this.assignedRides = new ArrayList<>();
        this.currentLocation = currentLocation;
    }

    // Accessor
    public List<Ride> getAssignedRides() {
        return assignedRides;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    // Mutator
    public void setAssignedRides(List<Ride> assignedRides) {
        this.assignedRides = assignedRides;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    // Methods
    public void assignRide(Ride ride) {
        assignedRides.add(ride);
    }

    public void updateLocation(String currentLocation) {
        setCurrentLocation(currentLocation);
        System.out.println("Driver location updated to: " + currentLocation);
    }

    public void trackPassenger(String passengerID) {

        int found = 0;

        for (Ride ride : this.assignedRides) {
            if (ride.getPassenger().getID().equals(passengerID)) {
                found = 1;
                System.out.println("Tracking Passenger...");
                System.out.println("Passenger ID: " + ride.getPassenger().getID());
                System.out.println("Passenger Name: " + ride.getPassenger().getName());
                break;
            }
        }
        if (found == 0) {
            System.out.println("No Passenger found with the ID: " + passengerID);
        }
    }
}
