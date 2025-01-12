import java.time.LocalDateTime;

public class WheelchairUser extends Passenger {

    public WheelchairUser(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    @Override
    public void bookRide(String pickupLocation, String dropoffLocation, Driver driver, double fare,
            LocalDateTime estimatedArrivalTime) {
        if (!driver.getVehicle().isWheelchairAccessible()) {
            System.out.println("Driver's vehicle is not wheelchair-accessible.");
            return;
        }

        double discount = 0.05; // 5% discount for wheelchair users
        double finalFare = fare - (fare * discount);
        super.bookRide(pickupLocation, dropoffLocation, driver, finalFare, estimatedArrivalTime);
        System.out.println("Discount applied for wheelchair user: " + discount * 100 + "%");
    }

    @Override
    public void preBookRide(String pickupLocation, String dropoffLocation, Driver driver, double fare,
            int daysInAdvance) {
        if (!driver.getVehicle().isWheelchairAccessible()) {
            System.out.println("Driver's vehicle is not wheelchair-accessible.");
            return;
        }

        // Determine discount based on days in advance
        double discount = 0;
        if (daysInAdvance == 1) {
            discount = 0.05; // 5% discount
        } else if (daysInAdvance == 2) {
            discount = 0.10; // 10% discount
        } else if (daysInAdvance == 3) {
            discount = 0.15; // 15% discount
        }

        discount = 0.05 + (0.05 * daysInAdvance);
        double finalFare = fare - (fare * discount); // Apply discount
        double advancePayment = finalFare * 0.05; // 5% advance payment
        

        if (withdraw(advancePayment)) {
            Ride ride = new Ride("PrebookedRide" + (getRides().size() + 1), this, driver, pickupLocation,
                    dropoffLocation, finalFare, LocalDateTime.now().plusDays(daysInAdvance), "Scheduled", true);
            Payment payment = new Payment("Payment" + (getRides().size() + 1), finalFare);
            ride.setPayment(payment);

            getRides().add(ride);
            driver.assignRide(ride);
            driver.deposit(advancePayment);

            System.out.println("Prebooked ride successfully created with discount: " + (discount * 100) + "%");
        } else {
            System.out.println("Insufficient funds to prebook the ride.");
        }
    }
}
