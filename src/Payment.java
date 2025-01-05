import java.time.LocalDateTime;

public class Payment {
    private String paymentID;
    private String rideID;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod; // e.g., "Credit Card", "Cash", "PayPal"
    private String status; // e.g., "Pending", "Completed", "Failed"

    public Payment(String paymentID, String rideID, double amount, LocalDateTime paymentDate, String paymentMethod, String status) {
        this.paymentID = paymentID;
        this.rideID = rideID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    // Accessors
    public String getPaymentID() {
        return paymentID;
    }

    public String getRideID() {
        return rideID;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    // Mutators
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Methods
    public void processPayment() {
       
        if ("Pending".equals(this.status)) {
            this.status = "Completed";
            System.out.println("Payment processed successfully for Payment ID: " + paymentID);
        } else {
            System.out.println("Payment is already " + this.status);
        }
    }

    public void displayPaymentDetails() {
        System.out.println("Payment Details:");
        System.out.println("Payment ID: " + paymentID);
        System.out.println("Ride ID: " + rideID);
        System.out.println("Amount: " + amount);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Status: " + status);
    }
}