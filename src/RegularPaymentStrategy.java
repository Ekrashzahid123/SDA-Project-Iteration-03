public class RegularPaymentStrategy implements PaymentStrategyInterface {

    @Override
    public void processPayment(Payment payment, double amount) {
        if ("Completed".equals(payment.getStatus())) {
            System.out.println("Payment is already completed.");
            return; 
        }
        System.out.println("Processing regular payment of " + amount + " for payment ID: " + payment.getPaymentID());
        
                payment.setStatus("Completed");
        System.out.println("Regular payment of " + amount + " processed successfully.");
    }
}
