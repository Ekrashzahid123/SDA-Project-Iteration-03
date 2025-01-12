public class RegularPaymentStrategy implements PaymentStrategyInterface {

    @Override
    public void processPayment(Payment payment, double amount) {
        // Check if payment is already completed
        if ("Completed".equals(payment.getStatus())) {
            System.out.println("Payment is already completed.");
            return;  // Avoid re-processing if already completed
        }

        // Set the amount for the payment (you can add more logic for actual payment processing)
        payment.setAmount(amount);
        
        // Proceed with payment processing (this is handled by the Payment class)
        System.out.println("Processing regular payment of " + amount + " for payment ID: " + payment.getPaymentID());
        
        // The actual payment logic can go here, for example, deducting the money from a user account

        // Mark payment as completed
        payment.setStatus("Completed");
        System.out.println("Regular payment of " + amount + " processed successfully.");
    }
}
