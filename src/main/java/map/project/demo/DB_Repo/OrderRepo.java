package map.project.demo.DB_Repo;

import map.project.demo.Entities.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Orders,Long> {
}
