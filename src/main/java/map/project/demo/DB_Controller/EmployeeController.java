package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.CartService;
import map.project.demo.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employee = (List<Employee>) employeeService.getAllEmployees();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            updatedEmployee.setId(id);
            Employee savedEmployee = employeeService.saveEmployee(updatedEmployee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{employeeId}/warehouse")
    public ResponseEntity<Warehouse> getWarehouseForEmployee(@PathVariable Long employeeId) throws Exception {
        Warehouse warehouse = employeeService.getWarehouseForEmployee(employeeId);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}/orders")
    public ResponseEntity<List<Orders>> getOrdersForEmployee(@PathVariable Long employeeId) throws Exception {
        List<Orders> orders= employeeService.getOrdersForEmployee(employeeId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee) {
        Employee savedEmployee = employeeService.saveEmployee(newEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }


    @GetMapping("/filterByRole")
    public List<Employee> filterByRole(@RequestParam String role) {
        return employeeService.filteredByRole(role);
    }
}
