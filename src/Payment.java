public class Payment {
    private String paymentID;
    private double amount;
    private String status;  // "Pending", "Completed"

    public Payment(String paymentID, double amount) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.status = "Pending";  // Initial payment status
    }

    // Accessors
    public String getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    //Mutators
    public void setPaymentID(String paymentID) {
        this.paymentID=paymentID;
    }

    public void setAmount(double amount) {
        this.amount=amount;
    }
    
    public void setStatus(String status) {
        this.status=status;
    }

    // Methods
    public void processPayment() {
        status = "Completed";
        System.out.println("Payment of " + amount + " has been completed.");
    }
}
