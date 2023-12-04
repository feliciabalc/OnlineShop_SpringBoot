package map.project.demo.DB_Repo;

import map.project.demo.Entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee,Long> {
}
