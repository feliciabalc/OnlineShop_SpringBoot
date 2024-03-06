package map.project.demo.DB_Repo;

import map.project.demo.Entities.Courier;
import org.springframework.data.repository.CrudRepository;

public interface CourierRepo extends CrudRepository<Courier,Long> {
}
