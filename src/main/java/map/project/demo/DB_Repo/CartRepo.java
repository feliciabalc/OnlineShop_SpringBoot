package map.project.demo.DB_Repo;

import map.project.demo.Entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart,Long> {
}
