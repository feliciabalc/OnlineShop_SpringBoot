package map.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "warehouse")
    private List<Articles> articles= new ArrayList<>();
    @OneToMany(mappedBy = "warehouse")
    private List<Employee> employees= new ArrayList<>();
    @OneToMany(mappedBy = "warehouse")
    private List<Suppliers> suppliers= new ArrayList<>();
    @OneToMany(mappedBy = "warehouse")
    private List<Courier> couriers= new ArrayList<>();


    public Warehouse(Long id,String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    protected Warehouse() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addArticles(Articles article){
        articles.add(article);
    article.setWarehouse(this);}

    public void removeArticle(Articles article){
        articles.remove(article);
        article.setWarehouse(null);
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
        employee.setWarehouse(this);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
        employee.setWarehouse(null);
    }

    public void addSuppliers(Suppliers supplier){
        suppliers.add(supplier);
        supplier.setWarehouse(this);
    }

    public void removeSuppliers(Suppliers supplier){
        suppliers.remove(supplier);
        supplier.setWarehouse(null);
    }

    public void addCourier(Courier courier){
        couriers.add(courier);
        courier.setWarehouse(this);
    }

    public void removeCourier(Courier courier){
        couriers.remove(courier);
        courier.setWarehouse(null);
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Suppliers> getSuppliers() {
        return suppliers;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }


    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
