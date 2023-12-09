package map.project.demo.Service;

import map.project.demo.DB_Repo.CartRepo;
import map.project.demo.Entities.Articles;
import map.project.demo.Entities.Cart;
import map.project.demo.Entities.Client;
import map.project.demo.Entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepo.findAll();
    }

    public Cart getCartById(Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepo.deleteById(id);
    }

    public List<Articles> getArticlesForCart(Long cartId) throws Exception {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new Exception("Cart not found with id " + cartId));

        return cart.getArticles();
    }

    public Client getClientForCart(Long cartId) throws Exception {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new Exception("Cart not found with id " + cartId));

        return cart.getClient();
    }



}
