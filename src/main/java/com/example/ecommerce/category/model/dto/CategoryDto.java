package com.example.ecommerce.category.model.dto;

import com.example.ecommerce.category.model.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class CategoryDto {
    private String name;
    private UUID parent_category_id;
    public static Category convertToCategory(CategoryDto categoryDto){
        return Category.builder()
                .name(categoryDto.getName())
                .parentCategory(categoryDto.getParent_category_id())
                .build();
    }
    public static CategoryDto convertToCategoryDto(Category category){
        return CategoryDto.builder()
                .name(category.getName())
                .parent_category_id(category.getParentCategory())
                .build();
    }
}
