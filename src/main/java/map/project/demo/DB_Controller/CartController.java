package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.ArticleService;
import map.project.demo.Service.CartService;
import map.project.demo.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final ClientService clientService;

    private final ArticleService articleService;

    public CartController(CartService cartService, ClientService clientService, ArticleService articleService) {
        this.cartService = cartService;
        this.clientService = clientService;
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);

        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCart() {
        List<Cart> cart = (List<Cart>) cartService.getAllCarts();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cartId}/articles")
    public ResponseEntity<List<Articles>> getArticlesForCart(@PathVariable Long cartId) throws Exception {
        List<Articles> articles = cartService.getArticlesForCart(cartId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{cartId}/client")
    public ResponseEntity<Client> getClientsForCart(@PathVariable Long cartId) throws Exception {
        Client client = cartService.getClientForCart(cartId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart existingCart = cartService.getCartById(id);
        if (existingCart != null) {
            updatedCart.setId(id);
            Cart savedCart = cartService.saveCart(updatedCart);
            ClientCartObserver observer = new ClientCartObserver(savedCart.getObservers());
            if(observer != null)
                savedCart.notifyObservers();
            return new ResponseEntity<>(savedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart newCart) {
        Cart savedCart = cartService.saveCart(newCart);
        return new ResponseEntity<>(savedCart, HttpStatus.OK);

    }

    @PostMapping("/{cartId}/addClient")
    public ResponseEntity<String> addClientToCart(@PathVariable Long cartId, @RequestBody Long clientId) throws Exception {
        Cart cart = cartService.getCartById(cartId);
        Client client = clientService.getClientById(clientId);

        if (cart == null || client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart or Client not found");
        }

        cartService.addClientToCart(cartId, client);

        return ResponseEntity.ok("Client added to the cart successfully");
    }

    @PostMapping("/{cartId}/addArticle")
    public ResponseEntity<String> addArticleToCart(@PathVariable Long cartId, @RequestBody Long articleId) throws Exception {
        Cart cart = cartService.getCartById(cartId);
        Articles article = articleService.getArticleById(articleId);

        if (cart == null || article == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart or Article not found");
        }

        cartService.addArticlesToCart(cartId, article);

        return ResponseEntity.ok("Article added to the cart successfully");
    }

}
