package map.project.demo.DB_Repo;

import map.project.demo.Entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client,Long> {
}
