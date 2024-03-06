package map.project.demo.Entities;

import org.springframework.stereotype.Component;

@Component
public class WorkersFactory {
    public Employee createEmployee(String name, String salary, double telefon, String role) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);
        employee.setTelefon(telefon);
        employee.setRole(role);
        return employee;
    }

    public Courier createCourier(String name, String salary, double telefon) {
        Courier courier = new Courier();
        courier.setName(name);
        courier.setSalary(salary);
        courier.setTelefon(telefon);
        courier.setRole("Courier");
        return courier;
    }
}

