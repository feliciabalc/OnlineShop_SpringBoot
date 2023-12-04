package Service;

import map.project.demo.DB_Repo.ReviewRepo;
import map.project.demo.DB_Repo.WarehouseRepo;
import map.project.demo.Entities.Review;
import map.project.demo.Entities.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepo warehouseRepo;

    @Autowired
    public WarehouseService(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    public List<Warehouse> getAllWarehouses() {
        return (List<Warehouse>) warehouseRepo.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepo.findById(id).orElse(null);
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepo.deleteById(id);
    }
}
