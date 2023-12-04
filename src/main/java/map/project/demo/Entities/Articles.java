package map.project.demo.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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
    private Long id;
    private String name;
    private String brand;
    private String material;
    private String type;
    private float price;

    protected Articles() {

    }

    @OneToMany(mappedBy = "article")
    private List<Specifications> specifications = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "article_order",
            joinColumns = @JoinColumn(name = "ArticleId"),
            inverseJoinColumns = @JoinColumn(name = "OrderId")
    )
    private List<Order> orders = new ArrayList<>();


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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addSpecifications(Specifications specification) {
        specifications.add(specification);
    }

    public void removeSpecifications(Specifications specification) {
        specifications.remove(specification);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public void addOrders(Order order) {
        orders.add(order);
    }

    public void removeOrders(Order order) {
        orders.remove(order);
    }
}