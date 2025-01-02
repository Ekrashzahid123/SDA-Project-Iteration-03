public class Driver extends User {
    public Driver(String id, String name, String email, String phone, String password) {
        super(id,name,email,phone,password);
    }

    public void acceptRide(String rideId) {
        System.out.println("Accepting ride: " + rideId);
    }

    public void rejectRide(String rideId) {
        System.out.println("Rejecting ride: " + rideId);
    }

    public void updateLocation(String location) {
        System.out.println("Updating location to: " + location);
    }

    public void ratePassenger(int rating, String feedback) {
        System.out.println("Rating passenger with " + rating + " stars. Feedback: " + feedback);
    }
}
//done