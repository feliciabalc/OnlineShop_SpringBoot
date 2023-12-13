package map.project.demo.Service;

import map.project.demo.DB_Repo.CourierRepo;
import map.project.demo.DB_Repo.EmployeeRepo;
import map.project.demo.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkersService {
    private final WorkersFactory workersFactory;

    @Autowired
    public WorkersService(WorkersFactory workersFactory) {
        this.workersFactory = workersFactory;
    }

    public Employee createEmployee(String name, String salary, double telefon, String role) {
        return workersFactory.createEmployee(name, salary, telefon, role);
    }

    public Courier createCourier(String name, String salary, double telefon) {
        return workersFactory.createCourier(name, salary, telefon);
    }


}
