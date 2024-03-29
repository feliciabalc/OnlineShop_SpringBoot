package map.project.demo.Service;

import map.project.demo.DB_Repo.CourierRepo;
import map.project.demo.DB_Repo.EmployeeRepo;
import map.project.demo.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public Warehouse getWarehouseForEmployee(Long employeeId) throws Exception {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found with id " + employeeId));

        return employee.getWarehouse();
    }

    public List<Orders> getOrdersForEmployee(Long employeeId) throws Exception {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found with id " + employeeId));

        return employee.getOrders();
    }

    public List<Employee> filteredByRole(String role){
        return employeeRepo.filteredByRole(role);
    }

    public void addWarehouseToEmployee(Long employeeId, Warehouse warehouse) throws Exception {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found with id " + employeeId));

        employee.setWarehouse(warehouse);
        employeeRepo.save(employee);
    }

    public void addOrderToEmployee(Long employeeId, Orders order) throws Exception {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found with id " + employeeId));

        employee.addOrder(order);
        employeeRepo.save(employee);
    }
}
