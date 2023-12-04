package map.project.demo.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    private Long id;
    private double orderNumber;
    private float totalAmount;
    private  String paymentMethod;
    private String address;
    private String date;
    @ManyToMany(mappedBy = "orders")
    private List<Articles> articles;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
  //  private PaymentStrategy paymentStrategy;

    protected Order() {

    }


//    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
//        this.paymentStrategy = paymentStrategy;
//    }
//
//    public void processPayment() {
//        paymentStrategy.processPayment(this);
//    }
//
//    public PaymentStrategy getPaymentStrategy() {
//        return paymentStrategy;
//    }

    public Order(Long id, double orderNumber, float totalAmount, String paymentMethod, String address,
                 String date) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.date = date;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(double orderNumber) {
        this.orderNumber = orderNumber;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderNumber=" + orderNumber +
                ", totalAmount=" + totalAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                '}';
    }



    public void addArticle(Articles article){
        articles.add(article);
    }

    public void removeArticle(Articles article){
        articles.remove(article);
    }
}
