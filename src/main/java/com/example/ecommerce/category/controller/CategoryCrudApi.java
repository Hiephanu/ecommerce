package com.example.ecommerce.category.controller;

import com.example.ecommerce.category.model.dto.CategoryDto;
import com.example.ecommerce.category.model.entity.Category;
import com.example.ecommerce.category.service.CategoryCrudService;
import com.example.ecommerce.share.api.ResponseBody;
import com.example.ecommerce.share.api.ResponseEntityFactory;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryCrudApi {
    private final CategoryCrudService categoryCrudService;
    @GetMapping("/{id}")
    public Result<Category, DomainException> getCategoryById(@PathVariable UUID id) {
        return null;
    }

    @PostMapping("")
    public Result<CategoryDto, DomainException> saveCategory(@RequestBody CategoryDto categoryDto){
        return  categoryCrudService.saveCategory(categoryDto);
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody> getRootCategory(){
        try {
            return ResponseEntityFactory.ok(categoryCrudService.getAllCategory());
        } catch (Exception e){
            return ResponseEntityFactory.internalServerError(e);
        }
    }
}
