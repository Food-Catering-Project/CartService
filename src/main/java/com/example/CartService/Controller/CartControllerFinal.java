package com.example.CartService.Controller;

import com.example.CartService.Dto.CartItemRequest;
import com.example.CartService.Entity.CartItem;
import com.example.CartService.Service.CartServiceFinal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart/api")
public class CartControllerFinal {

    private final CartServiceFinal cartServiceFinal;

    public CartControllerFinal(CartServiceFinal cartServiceFinal) {
        this.cartServiceFinal = cartServiceFinal;
    }

//    @PostMapping("/addToCart")
//    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody CartItem item) {
//        try {
//            Map<String, Object> response = cartServiceFinal.addToCart(item);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(Map.of("message", "Unable to addToCart", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
//
//        }
//    }
    @PostMapping("/addToCart")
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody CartItemRequest cartItemRequest) {
        try {
        Map<String, Object> response = cartServiceFinal.addToCart(cartItemRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(Map.of("message", "Unable to addToCart", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

       }
}

    @GetMapping("/getCartItems")
    public ResponseEntity<Map<String, Object>> getCartItems() {
        try {
            Map<String, Object> response = cartServiceFinal.getCartItems();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to getCartItems", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/increaseQuantity/{itemId}")
    public ResponseEntity<Map<String, Object>> increaseQuantity(@PathVariable Long itemId) {
        try {
            Map<String, Object> response = cartServiceFinal.increaseQuantity(itemId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to increaseQuantity", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/decreaseQuantity/{itemId}")
    public ResponseEntity<Map<String, Object>> decreaseQuantity(@PathVariable Long itemId) {
        try {
            Map<String, Object> response = cartServiceFinal.decreaseQuantity(itemId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to decreaseQuantity", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/removeItem/{itemId}")
    public ResponseEntity<Map<String, Object>> removeItem(@PathVariable Long itemId) {
        try {
            Map<String, Object> response = cartServiceFinal.removeItem(itemId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to remove item", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/clearCart")
    public ResponseEntity<Map<String, Object>> clearCart() {
        try {
            Map<String, Object> response = cartServiceFinal.clearCart();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Unable to Clear", "error", e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }
}
