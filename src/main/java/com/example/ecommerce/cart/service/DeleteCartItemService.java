package com.example.ecommerce.cart.service;

import com.example.ecommerce.cart.repository.CartItemRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteCartItemService {
    private final CartItemRepository cartItemRepository;
    private Result<Void, DomainException> deleteCartItem(String id){
        try{
            cartItemRepository.deleteById(UUID.fromString(id));
            return Result.success(null);
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("Internal error",""));
        }
    }
    private Result<Void, DomainException> deleteCartItems(List<String> ids){
        ids.forEach(id ->{
            cartItemRepository.deleteById(UUID.fromString(id));
        });
        return Result.success(null);
    }
}
