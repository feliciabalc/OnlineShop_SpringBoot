package map.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Courier")
public class Courier extends Employee{

    public Courier(Long id, String name, String salary, double telefon, String role) {
        super(id, name, salary, telefon, role);
    }

    protected Courier() {
        super();
    }

    @OneToMany(mappedBy = "courier")
    @JsonManagedReference
    protected List<Orders> orders= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", telefon=" + telefon +
                '}';
    }
}
