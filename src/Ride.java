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
    private Payment payment;
    private boolean isPreBooked;  // New flag to indicate if it's a pre-booked ride

    // Constructor
    public Ride(String rideID, Passenger passenger, Driver driver, String pickupLocation, String dropoffLocation,
                double fare, LocalDateTime estimatedArrivalTime, String status, boolean isPreBooked) {
        this.rideID = rideID;
        this.passenger = passenger;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.fare = fare;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.status = status;
        this.payment = null;
        this.isPreBooked = isPreBooked;  // Initialize pre-booked flag
    }

    // Accessors
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

    public Payment getPayment() {
        return payment;
    }

    public boolean isPreBooked() {
        return isPreBooked;
    }

    public boolean isPreScheduled() {
        return this.isPreBooked;
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

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    // Methods

    public void trackRide() {
        System.out.println("Tracking ride: ");
        System.out.println("Ride ID: " + rideID);
        System.out.println("Pickup Location: " + pickupLocation);
        System.out.println("Dropoff Location: " + dropoffLocation);
        System.out.println("Status: " + status);

        if (payment != null) {
            System.out.println("Payment Status: " + payment.getStatus());
            System.out.println("Payment Amount: " + payment.getAmount());
        } else {
            System.out.println("No payment details available.");
        }
    }

    public void completeRide() {
        if ("Completed".equals(this.status)) {
            System.out.println("Ride is already completed.");
            return;
        }

        this.status = "Completed";
        System.out.println("Ride " + this.rideID + " has been completed.");

        // Finalize payment if ride is completed
        if (this.payment != null) {
            this.payment.processPayment();
            if (this.isPreBooked) {
                // If the ride was pre-booked, add a small refund or bonus to passenger's wallet
                double refundAmount = fare * 0.05;  // Refund logic can be adjusted
                passenger.addWalletBalance(refundAmount);  // Add to passenger's wallet (if cancellation is involved)
            }
        } else {
            System.out.println("No payment associated with this ride.");
        }
    }

    // Method to handle ride cancellation
    public void cancelRide() {
        if (this.status.equals("Completed")) {
            System.out.println("Ride already completed, cannot be canceled.");
            return;
        }

        this.status = "Cancelled";
        System.out.println("Ride " + this.rideID + " has been canceled.");

        // If the ride was pre-booked, advance payment is not refunded
        if (this.isPreBooked) {
            System.out.println("Advance payment for this pre-booked ride will not be refunded.");
        } else {
            // Refund the full payment to the passenger's wallet for normal rides
            if (this.payment != null) {
                passenger.addWalletBalance(payment.getAmount());  // Refund the full payment to the passenger's wallet
                System.out.println("Payment refunded to passenger's wallet.");
            }
        }
    }
}
