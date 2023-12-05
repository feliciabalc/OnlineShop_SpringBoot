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

    public void generateBill(Orders orders){
        System.out.println("Generating bill for" + orders.getId() + " with the total amount of" + orders.getTotalAmount());
    }




}
