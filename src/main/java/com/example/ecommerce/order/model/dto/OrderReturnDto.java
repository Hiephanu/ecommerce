package com.example.ecommerce.order.model.dto;

import com.example.ecommerce.auth.model.dto.UserRegisterResponseDto;
import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.cart.model.entity.CartItem;
import com.example.ecommerce.voucher.model.entity.Voucher;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class OrderReturnDto {
    private UUID id;
    private User user;
    private Voucher voucher;
    private CartItem cartItem;
    private double totalPrice;
}
