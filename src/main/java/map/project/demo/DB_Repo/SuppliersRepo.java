package map.project.demo.DB_Repo;

import map.project.demo.Entities.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface SuppliersRepo extends JpaRepository<Suppliers,Long> {
    default List<Suppliers> filteredByName(String name) {
        List<Suppliers> suppliers = findAll();
        List<Suppliers> filteredSuppliers = new ArrayList<>();
        for (Suppliers item : suppliers) {
            if (Objects.equals(item.getName(), name))
                filteredSuppliers.add(item);
        }
        return filteredSuppliers;

    }


}
