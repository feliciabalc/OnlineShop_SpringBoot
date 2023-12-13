package map.project.demo.Service;

import jakarta.persistence.criteria.Order;
import map.project.demo.DB_Repo.ClientRepo;
import map.project.demo.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getAllClients() {
        return (List<Client>) clientRepo.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepo.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        return clientRepo.save(client);
    }

    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }


    public Cart getCartForClient(Long clientId) throws Exception {
        Client client= clientRepo.findById(clientId)
                .orElseThrow(() -> new Exception("Client not found with id " + clientId));

        return client.getCart();
    }

    public List<Review> getReviewForClient(Long clientId) throws Exception {
        Client client= clientRepo.findById(clientId)
                .orElseThrow(() -> new Exception("Client not found with id " + clientId));

        return client.getReview();
    }

    public List<Orders> getOrdersForClient(Long clientId) throws Exception {
        Client client= clientRepo.findById(clientId)
                .orElseThrow(() -> new Exception("Client not found with id " + clientId));

        return client.getOrders();
    }

    public List<Client> filteredByName(String name){
        return clientRepo.filteredByName(name);
    }

    public void addCartToClient(Long clientId, Cart cart) throws Exception {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new Exception("Client not found with id " + clientId));

        client.setCart(cart);
        clientRepo.save(client);
    }

    public void addOrderToClient(Long clientId, Orders order) throws Exception {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new Exception("Client not found with id " + clientId));

        client.addOrder(order);
        clientRepo.save(client);
    }

    public void addReviewToClient(Long clientId, Review review) throws Exception {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new Exception("Client not found with id " + clientId));

        client.addReview(review);
        clientRepo.save(client);
    }
}
