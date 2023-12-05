package map.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client{
    @Id
    private Long id;
    private  String  name;
    private String  address;
    private double telefon;
    @OneToMany(mappedBy = "client")
    private List<Orders> orders= new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<Review> reviews= new ArrayList();

    public Client(Long id,String name, String address, double telefon) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telefon = telefon;
    }

    protected Client() {

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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }



    public List<Review> getReview() {
        return reviews;
    }

    public void setReview(List<Review> review) {
        this.reviews = review;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void removeReview(Review review){
        reviews.remove(review);
    }

    public void addOrder(Orders orders){
        this.orders.add(orders);
    }

    public void removeOrders(Orders orders){
        this.orders.remove(orders);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telefon=" + telefon +
                '}';
    }






}
