package map.project.demo.DB_Repo;

import map.project.demo.Entities.Warehouse;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepo extends CrudRepository<Warehouse,Long> {
}
