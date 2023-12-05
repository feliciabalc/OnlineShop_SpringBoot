package map.project.demo.Entities;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    private String cardNumber;

    public CreditCardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(Orders orders) {
        System.out.println("Paid for order with id "+ orders.getId() + " :" + orders.getTotalAmount() + " using credit card: " + cardNumber);
    }
}
