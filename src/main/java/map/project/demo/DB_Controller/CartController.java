package map.project.demo.DB_Controller;

import map.project.demo.Entities.Cart;
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
}
