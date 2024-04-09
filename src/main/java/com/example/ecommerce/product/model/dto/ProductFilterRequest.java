package com.example.ecommerce.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProductFilterRequest {
    private double minPrice;
    private double maxPrice;
    private String origin;
    private int quantity;
}
