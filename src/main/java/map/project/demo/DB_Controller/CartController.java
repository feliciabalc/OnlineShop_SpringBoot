package map.project.demo.DB_Controller;

import map.project.demo.Entities.Articles;
import map.project.demo.Entities.Cart;
import map.project.demo.Entities.Client;
import map.project.demo.Entities.Orders;
import map.project.demo.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
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

}
