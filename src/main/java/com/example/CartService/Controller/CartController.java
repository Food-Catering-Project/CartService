package com.example.CartService.Controller;

import com.example.CartService.Entity.Cart;
import com.example.CartService.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart/api")
public class CartController {


    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/getCartItems/{userId}")
    public ResponseEntity<Map<String, Object>> getCartItems(@PathVariable Long userId) {
        try{
            Map<String, Object> cartItems  = cartService.getCartItems(userId);
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to get cart Items", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add/{userId}/{menuId}")
    public ResponseEntity<Map<String, Object>> addToCart(@PathVariable Long userId, @PathVariable Long menuId, @RequestBody Cart cart) {
        try {
            Map<String, Object> response = cartService.addToCart(userId,menuId,cart);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to add", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Map<String, Object>> removeFromCart(@PathVariable Long cartId) {
        try {
            Map<String, Object> response = cartService.removeFromCart(cartId);
        return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to deelte", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<Map<String, Object>> updateCartItem(
            @PathVariable Long cartId, @RequestParam Long quantity) {
        try {
            Map<String, Object> response = cartService.updateCartItem(cartId, quantity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to update", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }



}
