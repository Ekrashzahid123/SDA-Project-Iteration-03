import java.time.LocalDateTime;

public class Ride {
    private String rideID;
    private Passenger passenger;
    private Driver driver;
    private String pickupLocation;
    private String dropoffLocation;
    private String status;
    private double fare;
    private LocalDateTime estimatedArrivalTime;

    public Ride(String rideID, Passenger passenger, Driver driver, String pickupLocation, String dropoffLocation,
            double fare, LocalDateTime estimatedArrivalTime, String status) {
        this.rideID = rideID;
        this.passenger = passenger;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.fare = fare;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.status = status;
    }

    //Rest of code remian will be start from tommorrow// staring by Ekrash on 4 Dec 2024 



//Accessors
public String getRideID() {
    return rideID;
}

public Passenger getPassenger() {
    return passenger;
}

public Driver getDriver() {
    return driver;
}

public String getPickupLocation() {
    return pickupLocation;
}

public String getDropoffLocation() {
    return dropoffLocation;
}
public double getFare() {
    return fare;
}

public LocalDateTime getEstimatedArrivalTime() {
    return estimatedArrivalTime;
}

public String getStatus() {
    return status;
}

// Mutators
public void setRideID(String rideID) {
    this.rideID = rideID;
}

public void setPassenger(Passenger passenger) {
    this.passenger = passenger;
}

public void setDriver(Driver driver) {
    this.driver = driver;
}
public void setPickupLocation(String pickupLocation) {
    this.pickupLocation = pickupLocation;
}

public void setDropoffLocation(String dropoffLocation) {
    this.dropoffLocation = dropoffLocation;
}

public void setFare(double fare) {
    this.fare = fare;
}

public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
    this.estimatedArrivalTime = estimatedArrivalTime;
}

public void setStatus(String status) {
    this.status = status;
}
 //Methods
 public void trackRide() {
    System.out.println("Tracking ride: ");
    System.out.println("Ride ID: " + rideID);
    System.out.println("Pickup Location: " + pickupLocation);
    System.out.println("Dropoff Location: " + dropoffLocation);
    System.out.println("Status: " + status);
}

public void completeRide() {
    System.out.println("Ride " + rideID + " has been completed.");
    this.status = "Completed";}
}







