package map.project.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    private Long id;
    private double quantity;


    @OneToMany(mappedBy = "cart")
    private List<Articles> articles= new ArrayList<>();

    //private List<ClientCartObserver> observers = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private Client client;

    protected
    Cart() {

    }


//    public List<ClientCartObserver> getObservers() {
//        return observers;
//    }
//    public void setObservers(List<ClientCartObserver> observers) {
//        this.observers = observers;
//    }
//    public List<ClientCartObserver> getCartObservers(Cart cart) {
//        return cart.observers;
//    }




    public Cart(Long id,double quantity) {
        this.id = id;
        this.quantity = quantity;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }



    public void addArticles(Articles article){
        articles.add(article);
    }

    public void removeArticles(Articles article){
        articles.remove(article);
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }




//    public void addObserver(ClientCartObserver observer) {
//        observers.add(observer);
//    }
//
//    public void removeObserver(ClientCartObserver observer) {
//        observers.remove(observer);
//    }
//
//    public void notifyObservers(){
//        for(CartObserver obs: observers)
//            obs.update(this);
//    }


}
