package map.project.demo.DB_Repo;

import map.project.demo.Entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {
    default List<Warehouse> filteredByAddress(String address) {
        List<Warehouse> warehouse = findAll();
        List<Warehouse> filteredWarehouse = new ArrayList<>();
        for (Warehouse item : warehouse) {
            if (Objects.equals(item.getAddress(), address))
                filteredWarehouse.add(item);
        }
        return filteredWarehouse;

    }
}
