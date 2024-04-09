package com.example.ecommerce.product.model.dto;

import com.example.ecommerce.category.model.entity.Category;
import com.example.ecommerce.product.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String title;
    private String image;
    private float price;
    private String origin;
    private int quantity;
    private List<Category> categoryList;
    public static ProductDto convertToProductDto(Product product,List<Category> list){
        return  ProductDto.builder()
                .id(product.getId().toString())
                .title(product.getTitle())
                .image(product.getImage())
                .price(product.getPrice())
                .origin(product.getOrigin())
                .quantity(product.getQuantity())
                .categoryList(list)
                .build();
    }
}
