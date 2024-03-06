                          ONLINE CLOTHING SHOP
This application provides an efficient way of purchasing and exploring many clothing items. "It also provides an easy way to 
search, filter, and order your favorite items using this app.



    ENTITIES
   1.1 Articles:
   The Articles entity represents an article product available in the online shop.

    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Articles {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String brand;
        private String material;
        private String type;
        private float price;
   1.2 Cart:
   The Cart entity represents a shopping cart associated with a customer.
   
    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private double quantity;
   1.3 Client:
   The Client entity represents a customer who can make purchases on the online shop.
    
    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Client{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private  String  name;
        private String  address;
        private double telefon;
   1.4 Courier:
   The Courier entity represents a delivery courier used for shipping orders. Courier entity inherits from Employee entity.

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

   1.5 Employee:
   The Employee entity represents an employee working within the online shop.

    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        protected Long id;
        protected String name;
        protected String role;
        protected String salary;
        protected double telefon;
   1.6 Orders:
   The Orders entity represents an order placed by a customer.

    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Orders implements OrderComponent {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private double orderNumber;
        private float totalAmount;
        private  String PaymentMethod;
        private String address;
        private String date;
   1.7 Review:
   The Review entity represents a customer's feedback or review for a product.

    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Review implements ReviewComponent{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String stars;
        private String comment;
        private String date;
   1.8 Specifications:
   The Specifications entity represents a detailed specification along with description of a product.
   
    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Specifications {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String size;
        private String color;
        private double quantity;
        private boolean availability= getQuantity()>0;
   1.9 Supplier:
   The Supplier entity represents a supplier providing products to the online shop.
   
    @Entity
    @Table(name = "suppliers")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Suppliers {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private  String name;
        private  double telefon;
   1.10 Warehouse:
   The Warehouse entity represents a storage facility for maintaining product inventory.
   
    @Entity
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Warehouse {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String address;

2.Relationships:
Relationships are implemented using Spring Boot annotations: 

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();
2.1 Articles: •	Each article can have multiple specifications. •Each article can have multiple reviews. •Many-to-Many relationship with orders. •Many-to-One relationship with a warehouse.
2.2 Cart:•	One-to-Many relationship with articles present in the cart. •One-to-One relationship with a client.
2.3 Client:•	One-to-Many relationship with orders placed by the client. •One-to-One relationship with a shopping cart. •One-to-Many relationship with reviews submitted by the client.
2.4 Courier: •	One-to-Many relationship with orders delivered by the courier. •	Many-to-One relationship with a warehouse.
2.5 Employee:•	One-to-Many relationship with orders processed by the employee. •	Many-to-One relationship with a warehouse.
2.6 Orders:•	Many-to-Many relationship with articles included in the order. •	Many-to-One relationship with the client who placed the order. •	Many-to-One relationship with the employee who processed the order.
2.7 Review:•	Many-to-One relationship with the article being reviewed. •	Many-to-One relationship with the client who submitted the review.
2.8 Specifications: •	Many-to-One relationship with the article associated with the specifications.
2.9 Warehouse:•	One-to-Many relationship with articles stored in the warehouse. •	One-to-Many relationship with employees associated with the warehouse. •	One-to-Many relationship with suppliers associated with the warehouse. •	One-to-Many relationship with couriers associated with the warehouse.
    
    REPOSITORY
Each entity has a repository extended from the JPA , which enables CRUD operations such as save, find, delete.   

    public interface CartRepo extends CrudRepository<Cart,Long> {
    }
    
Some of the entities have some methods in addition to the JPA Repository, that are useful for filtering and sorting objects.

        default List<Articles> filteredByMaterial(String material) {
        List<Articles> articles = findAll();
        List<Articles> filteredArticles = new ArrayList<>();
        for (Articles item : articles) {
            if (Objects.equals(item.getMaterial(), material))
                filteredArticles.add(item);
        }
        return filteredArticles;


    }


    Controller
In the controller package are a handful of methods for each entity that serves for different functionalities. Each method is implemented
using the Rest architecture, you can find the endpoints in the Api.http file.
Some of the important and useful use-cases are:
-modifying you cart
       
        @PutMapping("/{id}")
        public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart existingCart = cartService.getCartById(id);
        if (existingCart != null) {
        updatedCart.setId(id);
        Cart savedCart = cartService.saveCart(updatedCart);
        return new ResponseEntity<>(savedCart, HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }
-reading the reviews for an article with an id given by the user
        
        @GetMapping("/{articleId}/review")
        public ResponseEntity<List<Review>> getReviewsForArticle(@PathVariable Long articleId) throws Exception {
        List<Review> review = articleService.getReviewForArticle(articleId);
        return new ResponseEntity<>(review, HttpStatus.OK);
        }
-displaying a client's cart or orders
        
            @GetMapping("/{clientId}/cart")
    public ResponseEntity<Cart> getCartForClient(@PathVariable Long clientId) throws Exception {
        Cart cart = clientService.getCartForClient(clientId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @GetMapping("/{clientId}/orders")
    public ResponseEntity<List<Orders>> getOrdersForClient(@PathVariable Long clientId) throws Exception {
        List<Orders> orders = clientService.getOrdersForClient(clientId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

-displaying the reviews, which can be filtered by a specific number of stars
-writing a review
        
        @GetMapping("/filterByStars")
        public List<Review> filterByStars(@RequestParam String stars) {
        return reviewService.filteredByStars(stars);
        }
If you want to test the above functionalities and eother ones, you can start the app and execute a request from the api.http file.
Also after you start the application you can click on this link and you will see the API documentation, which provides more details
about each request:
http://localhost:8080/swagger-ui/index.html

        DESIGN PATTERNS
We implemented some useful design patterns such as:
    Strategy Pattern
We implemented the Strategy Pattern, which in this app's case has the purpose of processing a payment based of the client's 
payment method. We have an interface(you can find it in the Entities package) and two classes CreditCard and CashOnDelievery that
implements the interface. This pattern is very useful when placing the order.

       PaymentStrategy paymentStrategy = null;
       if ("card".equals(savedOrder.getPaymentMethod())) {
       paymentStrategy = new CreditCardPaymentStrategy();
       } else if ("cash".equals(savedOrder.getPaymentMethod()))
       paymentStrategy = new CashOnDelieveryStrategy();
       if (paymentStrategy != null)
           paymentService.processPayment(savedOrder, paymentStrategy);


This code is part of the createOrder method. Using this pattern ensures that the client receives a message regarding the
chosen payment method.

 Singleton Pattern
Another handy pattern used in this app is the Singleton one. We used this pattern for generating bills when an order is created.
Since each bill belongs to only one order, this pattern is very convenient. You can find the implementation in the OrderBillingSystem
class(Entities package). It is used in the createOrder method:

        public ResponseEntity<Orders> createOrder(@RequestBody Orders newOrder) {
        Orders savedOrder = orderService.saveOrder(newOrder);
        orderBillingSystem.generateBill(savedOrder);


USE CASES:

1.Placing an order:

POST http://localhost:8080/api/order
{
"orderNumber": 1233,
"totalAmount": 140.0,
"address": "Gruia 57",
"date": "12.12.2022",
"articles": [],
"client": null,
"employee": null,
"paymentMethod": "cash"
}

2. Filter articles by a specific brand

GET http://localhost:8080/api/articles/filterByBrand?brand=zara

3. Displaying reviews based on the number of stars(here is used the Proxy pattern for hiding the reviews with less than 
2 stars when requesting to show all the reviews)
GET   http://localhost:8080/api/review/filterByStars?stars=5 stars

4.Displaying the specifications of an article
GET http://localhost:8080/api/specifications/5/articles

5. Display your cart
GET http://localhost:8080/api/cart/6

