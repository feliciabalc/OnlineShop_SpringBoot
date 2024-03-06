package map.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;
    protected String name;
    protected String role;
    protected String salary;
    protected double telefon;

    @OneToMany(mappedBy = "employee")
    protected List<Orders> orders= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Employee(Long id,String name, String salary, double telefon, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.telefon = telefon;
    }

    protected Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public double getTelefon() {
        return telefon;
    }

    public void setTelefon(double telefon) {
        this.telefon = telefon;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void addOrder(Orders order){
        orders.add(order);
        order.setEmployee(this);
    }

    public void removeOrder(Orders order){
        orders.remove(order);
        order.setEmployee(null);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", salary='" + salary + '\'' +
                ", telefon=" + telefon +
                '}';
    }
}
