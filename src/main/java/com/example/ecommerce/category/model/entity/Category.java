package com.example.ecommerce.category.model.entity;

import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.shop.model.entity.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "parent_category")
    private UUID parentCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tags")
    private String tags;

    @ManyToMany(mappedBy = "categories")
    private List<Shop> shops;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
