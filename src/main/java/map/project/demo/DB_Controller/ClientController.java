package map.project.demo.DB_Controller;


import map.project.demo.Entities.*;
import map.project.demo.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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

}
