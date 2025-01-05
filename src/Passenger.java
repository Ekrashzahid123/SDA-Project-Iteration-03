import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Passenger extends User {

    private List<Ride> rides;

    public Passenger(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
        this.rides = new ArrayList<>();
    }

    // Accessor
    public List<Ride> getRides() {
        return rides;
    }

    // Mutator
    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    // Methods
    public void bookRide(String pickupLocation, String dropoffLocation, Driver driver, double fare,
            LocalDateTime estimatedArrivalTime) {
        Ride ride = new Ride("Ride" + (rides.size() + 1), this, driver, pickupLocation, dropoffLocation, fare,
                estimatedArrivalTime, "In Progress");
        rides.add(ride);
        driver.assignRide(ride);
        System.out.println("Ride booked successfully: " + ride.getRideID());
    }

    public void trackRide(String id) {
        for (Ride ride : rides) {
            if (ride.getRideID() == id) {
                ride.trackRide();
                return;
            }
        }
        System.out.println("Ride not found");
    }

    public void trackDriver(String driverID) {
        int found = 0;
    
        for (Ride ride : this.rides) {
            if (ride.getDriver().getID().equals(driverID) && ride.getStatus().equals("In Progress")) {
                found = 1;
                System.out.println("Tracking Driver...");
                System.out.println("Driver ID: " + ride.getDriver().getID());
                System.out.println("Driver Name: " + ride.getDriver().getName());
                System.out.println("Driver Location: " + ride.getDriver().getCurrentLocation());
                break;
            }
        }
        if (found == 0) {
            System.out.println("No driver found with the ID: " + driverID);
        }
    }
    
}