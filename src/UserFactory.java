public class UserFactory {

    public static User createUser(String userType, String id, String name, String email, String phone, String password, Vehicle vehicle) {
        switch (userType.toLowerCase()) {
            case "driver":
                return new Driver(id, name, email, phone, password, "Unknown", vehicle);
            case "wheelchair":
                return new WheelchairUser(id, name, email, phone, password);
            case "passenger":
            default:
                return new Passenger(id, name, email, phone, password);
        }
    }
}