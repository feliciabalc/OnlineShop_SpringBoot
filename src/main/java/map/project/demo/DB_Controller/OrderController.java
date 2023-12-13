package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderBillingSystem orderBillingSystem = OrderBillingSystem.getInstance();
    private final OrderService orderService;

    private final ArticleService articleService;
    private final EmployeeService employeeService;

    private final ClientService clientService;

    private final PaymentService paymentService;

    public OrderController(OrderService orderService, ArticleService articleService, EmployeeService employeeService, ClientService clientService, PaymentService paymentService) {
        this.orderService = orderService;
        this.articleService = articleService;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.paymentService = paymentService;
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
        orderBillingSystem.generateBill(savedOrder);
        PaymentStrategy paymentStrategy = null;
        if ("card".equals(savedOrder.getPaymentMethod())) {
            paymentStrategy = new CreditCardPaymentStrategy();
        } else if ("cash".equals(savedOrder.getPaymentMethod()))
            paymentStrategy = new CashOnDelieveryStrategy();

        if (paymentStrategy != null)
            paymentService.processPayment(savedOrder, paymentStrategy);

        OrderComponent decoratedOrder = new DiscountDecorator(savedOrder);
        decoratedOrder.calculateTotalAmount();

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @GetMapping("/filterByDate")
    public List<Orders> filterByDate(@RequestParam String date) {
        return orderService.filteredByDate(date);
    }

    @PostMapping("/{orderId}/addClient")
    public ResponseEntity<String> addClientToOrder(@PathVariable Long orderId, @RequestBody Long clientId) throws Exception {
        Orders order = orderService.getOrderById(orderId);
        Client client = clientService.getClientById(clientId);

        if (order == null || client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order or Client not found");
        }

        orderService.addClientToOrder(orderId, client);

        return ResponseEntity.ok("Client added to the order successfully");
    }

    @PostMapping("/{orderId}/addEmployee")
    public ResponseEntity<String> addEmployeeToOrder(@PathVariable Long orderId, @RequestBody Long employeeId) throws Exception {
        Orders order = orderService.getOrderById(orderId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (order == null || employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order or Employee not found");
        }

        orderService.addEmployeeToOrder(orderId, employee);

        return ResponseEntity.ok("Employee added to the order successfully");
    }

    @PostMapping("/{orderId}/addArticle")
    public ResponseEntity<String> addArticleToOrder(@PathVariable Long orderId, @RequestBody Long articleId) throws Exception {
        Orders order = orderService.getOrderById(orderId);
        Articles article = articleService.getArticleById(articleId);

        if (order == null || article == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order or Article not found");
        }

        orderService.addArticleToOrder(orderId, article);

        return ResponseEntity.ok("Article added to the order successfully");
    }
}
