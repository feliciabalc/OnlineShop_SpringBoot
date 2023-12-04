package map.project.demo.Entities;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    private String cardNumber;

    public CreditCardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(Order order) {
        System.out.println("Paid for order with id "+ order.getId() + " :" + order.getTotalAmount() + " using credit card: " + cardNumber);
    }
}
