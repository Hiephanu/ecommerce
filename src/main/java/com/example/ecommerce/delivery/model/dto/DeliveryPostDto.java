package com.example.ecommerce.delivery.model.dto;

import com.example.ecommerce.delivery.model.entity.Delivery;
import com.example.ecommerce.delivery.model.enums.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class DeliveryPostDto {
    private String nameDelivery;
    private DeliveryType deliveryType;
    private double price;
    public static Delivery convertToDelivery(DeliveryPostDto deliveryPostDto){
        return Delivery.builder()
                .deliveryType(deliveryPostDto.deliveryType)
                .nameDelivery(deliveryPostDto.nameDelivery)
                .price(deliveryPostDto.price)
                .build();
    }
}
