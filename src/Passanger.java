public class Passanger extends User {
    public Passanger(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    public void bookRide(String pickupLocation, String dropoffLocation) {
        System.out.println("Booking ride from " + pickupLocation + " to " + dropoffLocation);
    }

    public void makePayment(String paymentMethod) {
        System.out.println("Making payment using: " + paymentMethod);
    }

    public void trackDriver(String driverId) {
        System.out.println("Tracking driver: " + driverId);
    }

    public void rateDriver(int rating, String feedback) {
        System.out.println("Rating driver with " + rating + " stars. Feedback: " + feedback);
    }
}
// done by Ekrash
