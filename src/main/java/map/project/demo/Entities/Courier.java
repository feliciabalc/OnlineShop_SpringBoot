package map.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Courier")
@SequenceGenerator(name = "courier_sequence", sequenceName = "courier_sequence", allocationSize = 1)
public class Courier extends Employee{

    public Courier(Long id, String name, String salary, double telefon, String role) {
        super(id, name, salary, telefon, role);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courier_sequence")
    @Override
    public Long getId() {
        return id;
    }

    protected Courier() {
        super();
    }

    @OneToMany(mappedBy = "courier")
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
