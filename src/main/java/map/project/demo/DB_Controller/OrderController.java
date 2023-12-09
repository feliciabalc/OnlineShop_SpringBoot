package map.project.demo.DB_Controller;

import jakarta.persistence.criteria.Order;
import map.project.demo.Entities.*;
import map.project.demo.Service.ArticleService;
import map.project.demo.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = orderService.getOrderById(id);

        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrder() {
        List<Orders> order= (List<Orders>) orderService.getAllOrders();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders updatedOrder) {
        Orders existingOrder = orderService.getOrderById(id);
        if (existingOrder != null) {
            updatedOrder.setId(id);
            Orders savedOrder = orderService.saveOrder(updatedOrder);
            return new ResponseEntity<>(savedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{orderId}/articles")
    public ResponseEntity<List<Articles>> getArticlesForOrder(@PathVariable Long orderId) throws Exception {
        List<Articles> articles= orderService.getArticlesForOrder(orderId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @GetMapping("/{orderId}/client")
    public ResponseEntity<Client> getClientForOrder(@PathVariable Long orderId) throws Exception {
        Client client= orderService.getClientForOrder(orderId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{orderId}/employee")
    public ResponseEntity<Employee> getEmployeeForOrder(@PathVariable Long orderId) throws Exception {
        Employee employee= orderService.getEmployeeForOrder(orderId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders newOrder) {
        Orders savedOrder = orderService.saveOrder(newOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @GetMapping("/filterByDate")
    public List<Orders> filterByDate(@RequestParam String date) {
        return orderService.filteredByDate(date);
    }
}
