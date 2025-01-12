public class WheelchairPaymentStrategy implements PaymentStrategyInterface {

    @Override
    public void processPayment(Payment payment, double amount) {
        double discount = 0.05;  // Wheelchair users get a 5% discount
        double discountedAmount = amount - (amount * discount);
        payment.setAmount(discountedAmount);
        payment.processPayment();
        System.out.println("Wheelchair user payment of " + discountedAmount + " processed.");
    }
}
