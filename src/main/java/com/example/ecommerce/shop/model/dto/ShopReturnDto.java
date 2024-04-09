package com.example.ecommerce.shop.model.dto;

import com.example.ecommerce.shop.model.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ShopReturnDto {
    private UUID id;
    private String name;
    private String address;
    private String avatar;
    private UUID user_id;
    private String description;
    private LocalDateTime created_at;
    public static ShopReturnDto convertToShopReturnDto(Shop shop){
        return  ShopReturnDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .address(shop.getAddress())
                .description(shop.getDescription())
                .user_id(shop.getUser().getId())
                .created_at(shop.getCreated_at())
                .build();
    }
}
