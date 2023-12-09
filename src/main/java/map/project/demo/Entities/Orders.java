package map.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double orderNumber;
    private float totalAmount;
    private  String PaymentMethod;
    private String address;
    private String date;


    // In Orders class
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "article_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Articles> articles = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
  //  private PaymentStrategy paymentStrategy;

    protected Orders() {

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

    public Orders(Long id, double orderNumber, float totalAmount, String PaymentMethod, String Address,
                  String Date) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.PaymentMethod = PaymentMethod;
        this.address = Address;
        this.date = Date;
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
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.PaymentMethod = paymentMethod;
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
                ", paymentMethod='" + PaymentMethod + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void addArticle(Articles article){

        articles.add(article);
        article.addOrders(this);
    }

    public void removeArticle(Articles article){
        articles.remove(article);
        article.removeOrders(this);
    }
}
