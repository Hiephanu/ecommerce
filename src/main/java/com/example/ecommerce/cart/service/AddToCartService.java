package com.example.ecommerce.cart.service;

import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.cart.model.dto.CartItemPostDto;
import com.example.ecommerce.cart.model.entity.CartItem;
import com.example.ecommerce.cart.repository.CartItemRepository;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AddToCartService {
    private final CartItemRepository cartItemRepository;
    private Result<CartItem, DomainException> addToCart(CartItemPostDto cartItemPostDto){
        try{
            CartItem cartItem = CartItem.builder()
                    .user(User.builder().id(UUID.fromString(cartItemPostDto.getUserId())).build())
                    .product(Product.builder().id(UUID.fromString(cartItemPostDto.getProductId())).build())
                    .quantity(cartItemPostDto.getQuantity())
                    .build();
            cartItemRepository.save(cartItem);
            return Result.success(cartItem);
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("Internal error","INTERNAL_ERROR"));
        }
    }
}
