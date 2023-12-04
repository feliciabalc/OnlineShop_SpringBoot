package map.project.demo.Entities;

import jakarta.persistence.Entity;

@Entity
public class Courier extends Employee{

    public Courier(Long id, String name, String salary, double telefon) {
        super(id, name, salary, telefon);
    }

    protected Courier() {
        super();
    }


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
