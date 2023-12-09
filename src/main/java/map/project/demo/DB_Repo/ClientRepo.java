package map.project.demo.DB_Repo;

import map.project.demo.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ClientRepo extends JpaRepository<Client,Long> {

    default List<Client> filteredByName(String name) {
        List<Client> clients = findAll();
        List<Client> filteredClient = new ArrayList<>();
        for (Client item : clients) {
            if (item.getName().contains(name))
                filteredClient.add(item);
        }
        return filteredClient;

    }

}
