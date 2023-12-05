package map.project.demo.Service;

import map.project.demo.DB_Repo.ClientRepo;
import map.project.demo.Entities.Cart;
import map.project.demo.Entities.Client;
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

}
