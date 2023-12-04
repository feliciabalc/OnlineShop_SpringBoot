package map.project.demo.DB_Repo;

import map.project.demo.Entities.Suppliers;
import org.springframework.data.repository.CrudRepository;

public interface SuppliersRepo extends CrudRepository<Suppliers,Long> {
}
