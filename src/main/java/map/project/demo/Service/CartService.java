package map.project.demo.Service;

import map.project.demo.DB_Repo.CartRepo;
import map.project.demo.Entities.Cart;
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


}
