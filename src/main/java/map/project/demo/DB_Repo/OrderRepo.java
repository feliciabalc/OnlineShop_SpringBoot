package map.project.demo.DB_Repo;

import map.project.demo.Entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order,Long> {
}
