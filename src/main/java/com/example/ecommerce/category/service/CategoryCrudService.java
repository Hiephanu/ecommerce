package com.example.ecommerce.category.service;

import com.example.ecommerce.category.model.dto.CategoryDto;
import com.example.ecommerce.category.model.dto.CategoryReturnDto;
import com.example.ecommerce.category.model.entity.Category;
import com.example.ecommerce.category.repository.CategoryRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryCrudService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public Result<CategoryDto, DomainException> getCategoryByName(String name){
        Category category = categoryRepository.findByName(name);
        if(category.getCategoryId() == null){
            return Result.fail(new DomainException("Not found","NOT_FOUND"));
        } else {
            CategoryDto categoryDto = CategoryDto.convertToCategoryDto(category);
            return  Result.success(categoryDto);
        }
    }
    public Result<List<CategoryReturnDto>,DomainException> getAllCategory(){
        List<CategoryReturnDto> categoryReturnDtos = new ArrayList<>();
        List<Category> categories = categoryRepository.findAllByParentCategory(null);
        if(categories.isEmpty()){
            return Result.fail(new DomainException("Not found","NOT_FOUND"));
        } else {
            categories.forEach(category -> {
                if(category.getParentCategory() != null){
                    List<Category> subCategory = categoryRepository.findAllByParentCategory(category.getCategoryId());
                    List<CategoryDto> subCategoryDtos = subCategory.stream()
                            .map(CategoryDto::convertToCategoryDto)
                            .collect(Collectors.toList());

                    CategoryReturnDto categoryReturnDto = CategoryReturnDto.builder()
                            .id(category.getCategoryId())
                            .rootCategory(category.getName())
                            .categoryDtos(subCategoryDtos)
                            .build();
                    categoryReturnDtos.add(categoryReturnDto);
                } else {
                    CategoryReturnDto categoryReturnDtoNull = CategoryReturnDto.builder()
                            .rootCategory(category.getName())
                            .id(category.getCategoryId())
                            .categoryDtos(null)
                            .build();
                    categoryReturnDtos.add(categoryReturnDtoNull);
                }
            });
            return Result.success(categoryReturnDtos);
        }
    }
    public Result<CategoryDto, DomainException> getRootCategory(){
        List<Category> categories = categoryRepository.findAllByParentCategory(null);
        return null;
    }
    public Result<CategoryDto, DomainException> saveCategory(CategoryDto categoryDto){
        Category categoryConvert = CategoryDto.convertToCategory(categoryDto);
        Category  category = categoryRepository.save(categoryConvert);
        return  Result.success(categoryDto);
    }
    public  Result<List<CategoryDto>,DomainException> getSubCategoryByName(String name){
        return  null;
    }
}
