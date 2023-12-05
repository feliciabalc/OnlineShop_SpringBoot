package map.project.demo.Entities;

public class CashOnDelieveryStrategy implements PaymentStrategy{

    @Override
    public void processPayment(Orders orders) {
        System.out.println("Please make sure you pay for the order with id " + orders.getId()+ " :"+ orders.getTotalAmount()+ " in cash upon delivery.");
    }
}
