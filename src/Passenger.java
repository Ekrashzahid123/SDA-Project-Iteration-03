import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Passenger extends User {

    private double wallet; // Wallet to manage credits or funds
    private List<Ride> rides;

    public Passenger(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
        this.wallet = 0; // Initial wallet balance
        this.rides = new ArrayList<>();
    }

    // Accessors
    public List<Ride> getRides() {
        return rides;
    }

    public double getWallet() {
        return wallet;
    }

    // Mutators
    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public void deposit(double amount) {
        wallet += amount;
    }

    public boolean withdraw(double amount) {
        if (wallet >= amount) {
            wallet -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds in wallet.");
            return false;
        }
    }

    // Methods
    public void addWalletBalance(double amount) {
        this.wallet += amount;
        System.out.println("Wallet balance updated. New balance: " + wallet);
    }

    public void bookRide(String pickupLocation, String dropoffLocation, Driver driver, double fare,
            LocalDateTime estimatedArrivalTime) {

        PaymentStrategyInterface paymentStrategy = new RegularPaymentStrategy(); // Default payment strategy

        // Create the ride and assign payment strategy
        Ride ride = new Ride("Ride" + (rides.size() + 1), this, driver, pickupLocation, dropoffLocation, fare,
                estimatedArrivalTime, "In Progress", false);
        Payment payment = new Payment("Payment" + (rides.size() + 1), fare, paymentStrategy);
        ride.setPayment(payment);

        rides.add(ride);
        driver.assignRide(ride);

        System.out.println("Ride booked successfully: " + ride.getRideID());
        System.out.println("Payment initialized. Status: " + payment.getStatus());
    }

    public void preBookRide(String pickupLocation, String dropoffLocation, Driver driver, double fare,
            int daysInAdvance, PaymentStrategyInterface paymentStrategy) {

        // Determine discount based on days in advance
        double discount = 0;
        if (daysInAdvance == 1) {
            discount = 0.05; // 5% discount
        } else if (daysInAdvance == 2) {
            discount = 0.10; // 10% discount
        } else if (daysInAdvance == 3) {
            discount = 0.15; // 15% discount
        }

        double finalFare = fare - (fare * discount); // Apply discount
        double advancePayment = finalFare * 0.05; // 5% advance payment for pre-scheduled rides

        // Withdraw the advance payment from wallet
        if (withdraw(advancePayment)) {
            // Create ride and set payment
            Ride ride = new Ride("PreScheduledRide" + (rides.size() + 1), this, driver, pickupLocation, dropoffLocation,
                    finalFare, LocalDateTime.now().plusDays(daysInAdvance), "Scheduled", true);
            Payment payment = new Payment("Payment" + (rides.size() + 1), finalFare, paymentStrategy);
            ride.setPayment(payment);

            // Assign ride to passenger and driver
            rides.add(ride);
            driver.assignRide(ride);
            driver.deposit(advancePayment);

            System.out.println("Pre-scheduled ride booked successfully with a " + (discount * 100) + "% discount.");
        }
    }

    public void cancelPreBookRide(Ride ride) {
        if (rides.contains(ride) && ride.isPreScheduled()) {
            double advancePayment = ride.getFare() * 0.05; // 5% advance payment
            deposit(advancePayment); // Refund the advance payment

            // Deduct from the driver's wallet since the ride was canceled
            ride.getDriver().withdraw(advancePayment); // Return the advance payment to the driver
            ride.setStatus("Cancelled");

            // Remove from the passenger's ride list
            rides.remove(ride);

            System.out.println("Pre-scheduled ride canceled. Refund issued: " + advancePayment);
        } else {
            System.out.println("Ride not found or it's not a pre-scheduled ride.");
        }
    }

    public void cancelRide(Ride ride) {
        if (rides.contains(ride) && !ride.isPreScheduled()) {

            ride.setStatus("Cancelled");

            System.out.println("Ride with ID " + ride.getRideID() + " has been cancelled by the passenger.");
        } else {
            if (ride.isPreScheduled()) {
                System.out.println("Cannot cancel pre-booked ride. Use cancelPreBookRide instead.");
            } else {
                System.out.println("Ride not found in passenger's rides.");
            }
        }
    }

    public void completeRide(Ride ride) {
        for (Ride r : rides) {
            if (r.getRideID().equals(ride.getRideID())) {
                if (r.getPayment().getStatus().equals("Pending")) {
                    double fare = r.getPayment().getAmount();

                    if (wallet >= fare) {
                        // Deduct fare from wallet
                        wallet -= fare;

                        // Mark payment as completed
                        r.getPayment().setStatus("Completed");

                        // Mark ride as completed
                        r.completeRide();
                        r.getDriver().completeRide(ride);

                        System.out.println("Ride " + r.getRideID() + " has been completed.");
                        System.out.println("Payment of " + fare + " has been completed.");
                    } else {
                        System.out.println("Insufficient funds to complete the ride.");
                    }
                } else {
                    System.out.println("Payment is already completed for this ride.");
                }
                return;
            }
        }
        System.out.println("Ride not found for user: " + getName());
    }

    public void trackRide(String id) {
        for (Ride ride : rides) {
            if (ride.getRideID().equals(id)) {
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

    public void viewRideHistory() {
        System.out.println("=====================================");
        System.out.println("Ride History for " + getName() + ":");
        System.out.println("=====================================");

        if (rides.isEmpty()) {
            System.out.println("No rides found.");
        } else {
            for (Ride ride : rides) {
                System.out.println("\n-------------------------------");
                System.out.println("Ride ID       : " + ride.getRideID());
                System.out.println("Status        : " + ride.getStatus());
                System.out.println("Fare          : $" + ride.getFare());
                System.out.println("Pickup Location : " + ride.getPickupLocation());
                System.out.println("DropOff Location: " + ride.getDropoffLocation());
                System.out.println("Ride Date     : " + ride.getEstimatedArrivalTime());
                System.out.println("Driver Name   : " + ride.getDriver().getName());
                System.out.println("Driver ID     : " + ride.getDriver().getID());
                System.out.println("-------------------------------");
            }
        }
        System.out.println("=====================================");
    }
}
