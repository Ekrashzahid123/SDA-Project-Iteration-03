public class Payment {
    private String paymentID;
    private double amount;
    private String status; // "Pending", "Completed"
    private PaymentStrategyInterface paymentStrategy;

    public Payment(String paymentID, double amount, PaymentStrategyInterface paymentStrategy) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.status = "Pending";
        this.paymentStrategy = paymentStrategy;
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

    // Mutators
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Methods
    public void processPayment() {
        if ("Completed".equals(this.status)) {
            System.out.println("Payment has already been processed.");
            return;
        }

        if (this.amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }

        paymentStrategy.processPayment(this, amount);
        this.status = "Completed"; // Set status to completed after processing
        System.out.println("Payment of " + amount + " has been completed.");
    }

    public void setPaymentStrategy(PaymentStrategyInterface paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}
