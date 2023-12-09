package map.project.demo.Service;

import map.project.demo.DB_Repo.OrderRepo;
import map.project.demo.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Orders> getAllOrders() {
        return (List<Orders>) orderRepo.findAll();
    }

    public Orders getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public Orders saveOrder(Orders orders) {
        return orderRepo.save(orders);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    public List<Articles> getArticlesForOrder(Long orderId) throws Exception {
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found with id " + orderId));

        return order.getArticles();
    }

    public Client getClientForOrder(Long orderId) throws Exception {
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found with id " + orderId));

        return order.getClient();
    }

    public Employee getEmployeeForOrder(Long orderId) throws Exception {
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found with id " + orderId));

        return order.getEmployee();
    }

    public List<Orders> filteredByDate(String date){
        return orderRepo.filteredByDate(date);
    }
}
