package com.example.ecommerce.category.model.dto;

import com.example.ecommerce.category.model.entity.Category;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryReturnDto {
    private UUID id;
    private String rootCategory;
    private List<CategoryDto> categoryDtos;
}
