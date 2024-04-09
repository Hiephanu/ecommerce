package com.example.ecommerce.category.repository;

import com.example.ecommerce.category.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findByName(String name);
    Page<Category> findByProductsId(UUID productId, Pageable pageable);
    List<Category> findAllByParentCategory(UUID id);
}
