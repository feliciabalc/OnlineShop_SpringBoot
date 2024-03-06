package map.project.demo.Entities;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    @Override
    public void processPayment(Orders orders) {
        System.out.println("Paid with card for order with id "+ orders.getId() + " :" + orders.getTotalAmount());
    }
}
