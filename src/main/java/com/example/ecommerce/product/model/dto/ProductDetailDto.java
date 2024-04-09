package com.example.ecommerce.product.model.dto;


import com.example.ecommerce.category.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDetailDto {
    private UUID id;
    private List<Category> categories;
    private String title;
    private String image;
    private String summary;
    private String description;
    private float price;
    private int quantity;
    private String origin;
    private String shopId;
}
