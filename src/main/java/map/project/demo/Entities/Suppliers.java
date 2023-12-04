package map.project.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Suppliers {
    @Id
    private Long id;
    private  String name;
    private  double telefon;
    private String articleType;

    public Suppliers(Long id,String name, double telefon, String articleType) {
        this.id = id;
        this.name = name;
        this.telefon = telefon;
        this.articleType = articleType;
    }

    protected Suppliers() {

    }

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

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

    public double getTelefon() {
        return telefon;
    }

    public void setTelefon(double telefon) {
        this.telefon = telefon;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }


    @Override
    public String toString() {
        return "Suppliers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telefon=" + telefon +
                ", articleType='" + articleType + '\'' +
                '}';
    }
}
