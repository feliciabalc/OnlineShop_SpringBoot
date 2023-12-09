package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.CartService;
import map.project.demo.Service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse= warehouseService.getWarehouseById(id);

        if (warehouse != null) {
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouse() {
        List<Warehouse> warehouse = (List<Warehouse>) warehouseService.getAllWarehouses();
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse updatedWarehouse) {
        Warehouse existingWarehouse = warehouseService.getWarehouseById(id);
        if (existingWarehouse != null) {
            updatedWarehouse.setId(id);
            Warehouse savedWarehouse =warehouseService.saveWarehouse(updatedWarehouse);
            return new ResponseEntity<>(savedWarehouse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{warehouseId}/articles")
    public ResponseEntity<List<Articles>> getArticlesForWarehouse(@PathVariable Long warehouseId) throws Exception {
        List<Articles> articles= warehouseService.getArticlesForWarehouse(warehouseId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}/suppliers")
    public ResponseEntity<List<Suppliers>> getSuppliersForWarehouse(@PathVariable Long warehouseId) throws Exception {
        List<Suppliers> suppliers= warehouseService.getSuppliersForWarehouse(warehouseId);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}/couriers")
    public ResponseEntity<List<Courier>> getCourierForWarehouse(@PathVariable Long warehouseId) throws Exception {
        List<Courier> couriers= warehouseService.getCouriersForWarehouse(warehouseId);
        return new ResponseEntity<>(couriers, HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesForWarehouse(@PathVariable Long warehouseId) throws Exception {
        List<Employee> employees= warehouseService.getEmployeesForWarehouse(warehouseId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse newWarehouse) {
        Warehouse savedWarehouse = warehouseService.saveWarehouse(newWarehouse);
        return new ResponseEntity<>(savedWarehouse, HttpStatus.OK);
    }
}
