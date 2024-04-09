package com.example.ecommerce.cart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartItemPostDto {
    private String productId;
    private int quantity;
    private String userId;
}
