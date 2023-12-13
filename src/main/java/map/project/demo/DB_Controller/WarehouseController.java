package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final EmployeeService employeeService;
    private final CourierService courierService;
    private final SupplierService supplierService;
    private final ArticleService articleService;

    public WarehouseController(WarehouseService warehouseService, EmployeeService employeeService, CourierService courierService, SupplierService supplierService, ArticleService articleService) {
        this.warehouseService = warehouseService;
        this.employeeService = employeeService;
        this.courierService = courierService;
        this.supplierService = supplierService;
        this.articleService = articleService;
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
    @GetMapping("/filterByAddress")
    public List<Warehouse> filteredByAddress(@RequestParam String address) {
        return warehouseService.filteredByAddress(address);
    }

    @PostMapping("/{warehouseId}/addCourier")
    public ResponseEntity<String> addCourierToWarehouse(@PathVariable Long warehouseId, @RequestBody Long courierId) throws Exception {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        Courier courier = courierService.getCourierById(courierId);

        if (warehouse == null || courier == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse or Courier not found");
        }

        warehouseService.addCourierToWarehouse(warehouseId, courier);

        return ResponseEntity.ok("Courier added to the warehouse successfully");
    }

    @PostMapping("/{warehouseId}/addSupplier")
    public ResponseEntity<String> addSupplierToWarehouse(@PathVariable Long warehouseId, @RequestBody Long supplierId) throws Exception {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        Suppliers supplier = supplierService.getSupplierById(supplierId);

        if (warehouse == null || supplier == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse or Supplier not found");
        }

        warehouseService.addSupplierToWarehouse(warehouseId, supplier);

        return ResponseEntity.ok("Supplier added to the warehouse successfully");
    }

    @PostMapping("/{warehouseId}/addEmployee")
    public ResponseEntity<String> addEmployeeToWarehouse(@PathVariable Long warehouseId, @RequestBody Long employeeId) throws Exception {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (warehouse == null || employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse or Employee not found");
        }

        warehouseService.addEmployeeToWarehouse(warehouseId, employee);

        return ResponseEntity.ok("Employee added to the warehouse successfully");
    }

    @PostMapping("/{warehouseId}/addArticle")
    public ResponseEntity<String> addArticleToWarehouse(@PathVariable Long warehouseId, @RequestBody Long articleId) throws Exception {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        Articles article = articleService.getArticleById(articleId);

        if (warehouse == null || article == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse or Article not found");
        }

        warehouseService.addArticleToWarehouse(warehouseId, article);

        return ResponseEntity.ok("Article added to the warehouse successfully");
    }
}
