public class Vehicle {

    private String vehicleID;
    private String make;
    private String model;
    private String licensePlate;
    private boolean wheelchairAccessible;

    // Constructor
    public Vehicle(String vehicleID, String make, String model, String licensePlate, boolean wheelchairAccessible) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.wheelchairAccessible = wheelchairAccessible;
    }

    // Accessors
    public String getVehicleID() {
        return vehicleID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    // Mutators
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    // Methods
    public void displayVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleID);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Wheelchair Accessible: " + wheelchairAccessible);
    }
}