package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.CartService;
import map.project.demo.Service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Suppliers> getSupplierById(@PathVariable Long id) {
        Suppliers supplier = supplierService.getSupplierById(id);

        if (supplier != null) {
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Suppliers>> getAllSupplier() {
        List<Suppliers> supplier = (List<Suppliers>) supplierService.getAllSuppliers();
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suppliers> updateSupplier(@PathVariable Long id, @RequestBody Suppliers updatedSupplier) {
        Suppliers existingSupplier = supplierService.getSupplierById(id);
        if (existingSupplier != null) {
            updatedSupplier.setId(id);
            Suppliers savedSupplier = supplierService.saveSupplier(updatedSupplier);
            return new ResponseEntity<>(savedSupplier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{supplierId}/warehouse")
    public ResponseEntity<Warehouse> getWarehouseForSupplier(@PathVariable Long supplierId) throws Exception {
        Warehouse warehouse= supplierService.getWarehouseForSupplier(supplierId);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Suppliers> createSupplier(@RequestBody Suppliers newSupplier) {
        Suppliers savedSupplier = supplierService.saveSupplier(newSupplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.OK);
    }

}
