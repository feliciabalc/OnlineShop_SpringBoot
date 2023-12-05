package map.project.demo.DB_Controller;

import map.project.demo.Entities.Cart;
import map.project.demo.Entities.Warehouse;
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

}
