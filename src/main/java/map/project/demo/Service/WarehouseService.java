package map.project.demo.Service;

import map.project.demo.DB_Repo.ReviewRepo;
import map.project.demo.DB_Repo.WarehouseRepo;
import map.project.demo.Entities.*;
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

    public List<Articles> getArticlesForWarehouse(Long warehouseId) throws Exception {
        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new Exception("Warehouse not found with id " + warehouseId));

        return warehouse.getArticles();
    }
    public List<Employee> getEmployeesForWarehouse(Long warehouseId) throws Exception {
        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new Exception("Warehouse not found with id " + warehouseId));

        return warehouse.getEmployees();
    }
    public List<Suppliers> getSuppliersForWarehouse(Long warehouseId) throws Exception {
        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new Exception("Warehouse not found with id " + warehouseId));

        return warehouse.getSuppliers();
    }
    public List<Courier> getCouriersForWarehouse(Long warehouseId) throws Exception {
        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new Exception("Warehouse not found with id " + warehouseId));

        return warehouse.getCouriers();
    }

    public List<Warehouse> filteredByAddress(String address) {
        return warehouseRepo.filteredByAddress(address);
    }
}
