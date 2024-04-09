package com.example.ecommerce.cart.repository;

import com.example.ecommerce.cart.model.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,UUID> {
    Page<CartItem> findAllByUserId(UUID userId, Pageable pageable);
}
