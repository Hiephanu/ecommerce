package com.example.ecommerce.order.model.entity;

import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.cart.model.entity.CartItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany
    private UUID voucherId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany
    @JoinColumn(name = "cartItemId")
    List<CartItem> cartItems;

}
