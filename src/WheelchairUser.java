import java.time.LocalDateTime;

public class WheelchairUser extends Passenger {

    public WheelchairUser(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    @Override
    public void bookRide(String pickupLocation, String dropoffLocation, Driver driver, double fare,
            LocalDateTime estimatedArrivalTime) {

        // Check if the vehicle is wheelchair accessible
        if (!driver.getVehicle().isWheelchairAccessible()) {
            System.out.println("Driver's vehicle is not wheelchair-accessible.");
            return;
        }

        // Apply discount for wheelchair users
        double discount = 0.05; // 5% discount for wheelchair users
        double finalFare = fare - (fare * discount);

        // Use the WheelchairPaymentStrategy for special payment processing
        PaymentStrategyInterface paymentStrategy = new WheelchairPaymentStrategy();
        Payment payment = new Payment("Payment" + (getRides().size() + 1), finalFare, paymentStrategy);

        // Create the ride with the adjusted fare and payment strategy
        Ride ride = new Ride("Ride" + (getRides().size() + 1), this, driver, pickupLocation, dropoffLocation,
                finalFare, estimatedArrivalTime, "In Progress", false);
        ride.setPayment(payment);

        getRides().add(ride);
        driver.assignRide(ride);

        System.out.println("Wheelchair user ride booked successfully: " + ride.getRideID());
        System.out.println(
                "Payment initialized with a " + (discount * 100) + "% discount. Status: " + payment.getStatus());
    }

   
    public void preBookRide(String pickupLocation, String dropoffLocation, Driver driver, double fare,
            int daysInAdvance, PaymentStrategyInterface paymentStrategy) {
        if (!driver.getVehicle().isWheelchairAccessible()) {
            System.out.println("Driver's vehicle is not wheelchair-accessible.");
            return;
        }

        // Determine discount based on days in advance
        double discount = 0;
        if (daysInAdvance == 1) {
            discount = 0.05; // 5% discount for wheelchair users
        } else if (daysInAdvance == 2) {
            discount = 0.10; // 10% discount for wheelchair users
        } else if (daysInAdvance == 3) {
            discount = 0.15; // 15% discount for wheelchair users
        }

        double finalFare = fare - (fare * discount); // Apply discount
        double advancePayment = finalFare * 0.05; // 5% advance payment for pre-scheduled rides

        // Withdraw the advance payment from wallet
        if (withdraw(advancePayment)) {
            // Create ride and set payment
            Ride ride = new Ride("PreScheduledRide" + (getRides().size() + 1), this, driver, pickupLocation,
                    dropoffLocation,
                    finalFare, LocalDateTime.now().plusDays(daysInAdvance), "Scheduled", true);
            Payment payment = new Payment("Payment" + (getRides().size() + 1), finalFare, paymentStrategy);
            ride.setPayment(payment);

            // Assign ride to passenger and driver
            getRides().add(ride);
            driver.assignRide(ride);
            driver.deposit(advancePayment);

            System.out.println("Wheelchair user pre-scheduled ride booked with a " + (discount * 100) + "% discount.");
        }
    }
    
    @Override
    public void completeRide(Ride ride) {
        super.completeRide(ride);
    }
}
