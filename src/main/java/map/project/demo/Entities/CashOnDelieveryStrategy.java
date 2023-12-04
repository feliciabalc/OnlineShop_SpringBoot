package map.project.demo.Entities;

public class CashOnDelieveryStrategy implements PaymentStrategy{

    @Override
    public void processPayment(Order order) {
        System.out.println("Please make sure you pay for the order with id " + order.getId()+ " :"+ order.getTotalAmount()+ " in cash upon delivery.");
    }
}
