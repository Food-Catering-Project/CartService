package com.example.CartService.Repository;

import com.example.CartService.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByMenuId(Long menuId);
}