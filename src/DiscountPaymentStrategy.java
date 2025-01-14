public class DiscountPaymentStrategy implements PaymentStrategyInterface {

    private double discount;

    public DiscountPaymentStrategy(double discount) {
        this.discount = discount;
    }

    @Override
    public void processPayment(Payment payment, double amount) {
        double discountedAmount = amount - (amount * discount);
        payment.setAmount(discountedAmount);
    }
}
