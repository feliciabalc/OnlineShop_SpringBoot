package map.project.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Specifications {
    @Id
    private Long id;
    private String size;
    private String color;
    private double quantity;
    private boolean availability= getQuantity()>0;

    @ManyToOne
    @JoinColumn(name = "articles_id")
    private Articles article;

    public Specifications(Long id, String size, String color, double quantity, boolean availability) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.availability = availability;
    }

    protected Specifications() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Specifications{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", quantity=" + quantity +
                ", availability=" + availability +
                '}';
    }

    public boolean getAvailability(){
        if(this.availability == true)
            return true;
        return false;
    }
}
