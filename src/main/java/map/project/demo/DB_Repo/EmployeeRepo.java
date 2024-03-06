package map.project.demo.DB_Repo;

import map.project.demo.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    default List<Employee> filteredByRole(String role) {
        List<Employee> employee = findAll();
        List<Employee> filteredEmployee = new ArrayList<>();
        for (Employee item : employee) {
            if (Objects.equals(item.getRole(), role))
                filteredEmployee.add(item);
        }
        return filteredEmployee;
    }

}
