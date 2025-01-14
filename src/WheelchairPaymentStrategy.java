public class WheelchairPaymentStrategy implements PaymentStrategyInterface {

    @Override
    public void processPayment(Payment payment, double amount) {
        if ("Completed".equals(payment.getStatus())) {
            System.out.println("Payment is already completed.");
            return; 
        }

        double discount = 0.05;
        double discountedAmount = amount - (amount * discount);
        payment.setAmount(discountedAmount); 

        payment.processPayment();  

        System.out.println("Wheelchair user payment of " + discountedAmount + " processed.");
    }
}
