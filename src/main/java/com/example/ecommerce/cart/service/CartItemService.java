package com.example.ecommerce.cart.service;

import com.example.ecommerce.cart.model.dto.CartItemPostDto;
import com.example.ecommerce.cart.model.entity.CartItem;
import com.example.ecommerce.cart.repository.CartItemRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.exception.NotFoundException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    public Result<CartItem,DomainException> getCartItemById(String id){
        Optional<CartItem> cartItem = cartItemRepository.findById(UUID.fromString(id));
        return cartItem.<Result<CartItem, DomainException>>map(Result::success).orElseGet(() -> Result.fail(new NotFoundException("Not found")));
    }
    public Result<List<CartItem>, DomainException> getAllCartItemByUserId(String userId,int page,int size){
        try {
            PageRequest pageRequest = PageRequest.of(page,size);
            Page<CartItem> cartItems = cartItemRepository.findAllByUserId(UUID.fromString(userId),pageRequest);
            return  Result.success(cartItems.getContent());
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("Internal error","INTERNAL_ERROR"));
        }
    }
    public Result<CartItem, DomainException> updateCartItem(CartItemPostDto cartItemPostDto,String id){
        try{
            Optional<CartItem> cartItem = cartItemRepository.findById(UUID.fromString(id));
            if(cartItem.isPresent()){
                cartItem.get().setQuantity(cartItemPostDto.getQuantity());
                return Result.success(cartItemRepository.save(cartItem.get()));
            } else {
                return Result.fail(new DomainException("Item cart not found","CART_ITEM_NOT_FOUND"));
            }
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("Internal error",""));
        }
    }


}
