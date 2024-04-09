package com.example.ecommerce.product.model.dto;

import com.example.ecommerce.category.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProductPostDto {
    private MultipartFile image;
    private String title;
    private String summary;
    private String description;
    private float price;
    private int quantity;
    private String origin;
    private String shopId;
    private List<Category> categories;
    private String tags;
}
