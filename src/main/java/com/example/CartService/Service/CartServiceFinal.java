package com.example.CartService.Service;

import com.example.CartService.Dto.CartItemRequest;
import com.example.CartService.Entity.CartItem;
import com.example.CartService.Repository.CartItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceFinal {

    private final CartItemRepository cartItemRepository;

    public CartServiceFinal(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

//    public Map<String, Object> addToCart(CartItem item) {
//        CartItem savedItem = cartItemRepository.findByMenuId(item.getMenuId())
//                .map(existingItem -> {
//                    existingItem.setQuantity(existingItem.getQuantity() + 1);
//                    existingItem.setTotalPrice(existingItem.getTotalPrice() + item.getPrice());
//                    return cartItemRepository.save(existingItem);
//                })
//                .orElseGet(() -> {
//                    item.setQuantity(1);
//                    item.setTotalPrice(item.getPrice());
//                    return cartItemRepository.save(item);
//                });
//
//        return Map.of(
//                "status", HttpStatus.CREATED.value(),
//                "message", "Item added to cart",
//                "data", savedItem
//        );
//    }

    public Map<String, Object> addToCart(CartItemRequest cartItemRequest) {
       try {
             for (CartItem item : cartItemRequest.getItems()) {
                 System.out.println("items :- " + item);
                cartItemRepository.save(item);

              }

          return Map.of(
                "status", HttpStatus.CREATED.value(),
                "message", "Items added to cart successfully",
                "data", cartItemRequest.getItems()
          );
       } catch (Exception e) {
            return Map.of(
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "message", "Failed to add items to cart",
                "error", e.getMessage()
        );
    }
}





    public Map<String, Object> getCartItems() {
        List<CartItem> items = cartItemRepository.findAll();
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Cart items fetched",
                "data", items
        );
    }

    public Map<String, Object> increaseQuantity(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId).orElseThrow(() ->
                new RuntimeException("Item not found"));

        item.setQuantity(item.getQuantity() + 1);
        item.setTotalPrice(item.getTotalPrice() + item.getPrice());
        CartItem updated = cartItemRepository.save(item);

        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Quantity increased",
                "data", updated
        );
    }

    public Map<String, Object> decreaseQuantity(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId).orElseThrow(() ->
                new RuntimeException("Item not found"));

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            item.setTotalPrice(item.getTotalPrice() - item.getPrice());
            CartItem updated = cartItemRepository.save(item);
            return Map.of(
                    "status", HttpStatus.OK.value(),
                    "message", "Quantity decreased",
                    "data", updated
            );
        } else {
            cartItemRepository.deleteById(itemId);
            return Map.of(
                    "status", HttpStatus.OK.value(),
                    "message", "Item removed from cart",
                    "data", null
            );
        }
    }

    public Map<String, Object> removeItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Item removed from cart",
                "data", null
        );
    }

    public Map<String, Object> clearCart() {
        cartItemRepository.deleteAll();
        return Map.of(
                "status", HttpStatus.OK.value(),
                "message", "Cart cleared successfully",
                "data", null
        );
    }
}
