package Service;

import map.project.demo.DB_Repo.EmployeeRepo;
import map.project.demo.DB_Repo.OrderRepo;
import map.project.demo.Entities.Employee;
import map.project.demo.Entities.Order;
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

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepo.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
