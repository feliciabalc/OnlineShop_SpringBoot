package map.project.demo.DB_Controller;


import map.project.demo.Entities.*;
import map.project.demo.Service.CartService;
import map.project.demo.Service.ClientService;
import map.project.demo.Service.OrderService;
import map.project.demo.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;
    private final CartService cartService;
    private final OrderService orderService;
    private final ReviewService reviewService;


    public ClientController(ClientService clientService, CartService cartService, OrderService orderService, ReviewService reviewService) {
        this.clientService = clientService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);

        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClient() {
        List<Client> client = (List<Client>) clientService.getAllClients();
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client existingClient = clientService.getClientById(id);
        if (existingClient != null) {
            updatedClient.setId(id);
            Client savedClient = clientService.saveClient(updatedClient);
            return new ResponseEntity<>(savedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{clientId}/cart")
    public ResponseEntity<Cart> getCartForClient(@PathVariable Long clientId) throws Exception {
        Cart cart = clientService.getCartForClient(clientId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @GetMapping("/{clientId}/orders")
    public ResponseEntity<List<Orders>> getOrdersForClient(@PathVariable Long clientId) throws Exception {
        List<Orders> orders = clientService.getOrdersForClient(clientId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{clientId}/review")
    public ResponseEntity<List<Review>> getReviewForClient(@PathVariable Long clientId) throws Exception {
        List<Review> reviews = clientService.getReviewForClient(clientId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
        Client savedClient = clientService.saveClient(newClient);
        return new ResponseEntity<>(savedClient, HttpStatus.OK);

    }

    @GetMapping("/filterByName")
    public List<Client> filterByName(@RequestParam String name) {
        return clientService.filteredByName(name);
    }

    @PostMapping("/{clientId}/addCart")
    public ResponseEntity<String> addCartToClient(@PathVariable Long clientId, @RequestBody Long cartId) throws Exception {
        Client client = clientService.getClientById(clientId);
        Cart cart = cartService.getCartById(cartId);

        if (client == null || cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client or Cart not found");
        }

        clientService.addCartToClient(clientId, cart);

        return ResponseEntity.ok("Cart added to the client successfully");
    }

    @PostMapping("/{clientId}/addOrder")
    public ResponseEntity<String> addOrderToClient(@PathVariable Long clientId, @RequestBody Long orderId) throws Exception {
        Client client = clientService.getClientById(clientId);
        Orders order = orderService.getOrderById(orderId);

        if (client == null || order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client or Order not found");
        }

        clientService.addOrderToClient(clientId, order);

        return ResponseEntity.ok("Order added to the client successfully");
    }
    @PostMapping("/{clientId}/addReview")
    public ResponseEntity<String> addReviewToClient(@PathVariable Long clientId, @RequestBody Long reviewId) throws Exception {
        Client client = clientService.getClientById(clientId);
        Review review = reviewService.getReviewById(reviewId);

        if (client == null || review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client or Review not found");
        }

        clientService.addReviewToClient(clientId, review);

        return ResponseEntity.ok("Review added to the client successfully");
    }

}
