package com.example.CartService.Service;

import com.example.CartService.Entity.Cart;
import com.example.CartService.Entity.Menu;
import com.example.CartService.Entity.User;
import com.example.CartService.Repository.CartRepository;
import com.example.CartService.Repository.MenuRepository;
import com.example.CartService.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService {


    private final  CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, MenuRepository menuRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    public Map<String, Object> getCartItems(Long userId) {
        List<Cart> cartItems =  cartRepository.findByUserUserId(userId);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "success","cartItems retrived successfully",
                "data",cartItems
        );
    }

    public Map<String, Object> addToCart(Long userId, Long menuId, Cart cart) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        Double menuPrice = menu.getPrice();
        Double totalAmount = menuPrice * cart.getQuantity();
        String menuname = menu.getName();


        cart.setPrice(totalAmount);
        cart.setQuantity(cart.getQuantity());
        cart.setUser(user);
        cart.setMenu(menu);
        cart.setMenuName(menuname);



        Cart addCart = cartRepository.save(cart);
        return Map.of(
                "status", HttpStatus.CREATED.value(),
                "success","cart added successfully",
                "data",addCart
        );
    }

    public  Map<String, Object>  removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "success","item removed successfully"

        );
    }

    public Map<String,Object> updateCartItem(Long cartId, Long quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Menu menu = cart.getMenu();
        Double menuPrice = menu.getPrice();
        Double totalAmount = menuPrice * quantity;

        cart.setQuantity(quantity);
        cart.setPrice(totalAmount);

         cartRepository.save(cart);
       return Map.of(
               "status", HttpStatus.OK.value(),
               "success","cart updated successfully",
               "data",cart
       );
    }
}
