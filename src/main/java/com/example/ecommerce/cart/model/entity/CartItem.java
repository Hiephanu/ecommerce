package com.example.ecommerce.cart.model.entity;

import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.delivery.model.entity.Delivery;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.voucher.model.entity.Voucher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Entity
@AllArgsConstructor
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cartItemId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToOne
    @JoinColumn(name = "deliveryId")
    private Delivery delivery;

    @OneToOne
    @JoinColumn(name = "voucherId")
    private Voucher voucher;

    private int quantity;
}
