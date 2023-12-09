package map.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Articles {
    public Articles(Long id, String name, String brand, String material, String type, float price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.material = material;
        this.type = type;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String material;
    private String type;
    private float price;

    protected Articles() {

    }

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Specifications> specifications = new ArrayList<>();


    // The mappedBy attribute specifies the field in the Review entity
    // that owns the relationship (i.e., the article field in the Review entity).
    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "articles")
    private List<Orders> orders = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getMaterial() {
        return material;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", material='" + material + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public List<Specifications> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specifications> specifications) {
        this.specifications = specifications;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void addSpecification(Specifications specification) {
        specifications.add(specification);
        specification.setArticle(this);
    }

    public void removeSpecifications(Specifications specification) {

        specifications.remove(specification);
        specification.setArticle(null);
    }

    public void addReview(Review review) {
        review.setArticle(this);
        reviews.add(review);

    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setArticle(null);
    }

    public void addOrders(Orders orders) {
        this.orders.add(orders);
        orders.addArticle(this);
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void removeOrders(Orders orders) {

        this.orders.remove(orders);
        orders.removeArticle(this);
    }
}