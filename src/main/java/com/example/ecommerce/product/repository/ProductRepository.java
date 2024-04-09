package com.example.ecommerce.product.repository;

import com.example.ecommerce.category.model.entity.Category;
import com.example.ecommerce.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByTitleContaining(String keyword, Pageable pageable);

}
