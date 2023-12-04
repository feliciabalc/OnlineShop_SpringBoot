package map.project.demo.Entities;

public class OrderBillingSystem {
    private static OrderBillingSystem instance;

    private OrderBillingSystem() {
    }

    public static OrderBillingSystem getInstance() {
       if(instance == null){
           synchronized (OrderBillingSystem.class){
               if(instance == null){
                   instance = new OrderBillingSystem();
               }
           }
       }return instance;
    }

    public void generateBill(Order order){
        System.out.println("Generating bill for" + order.getId() + " with the total amount of" + order.getTotalAmount());
    }




}
