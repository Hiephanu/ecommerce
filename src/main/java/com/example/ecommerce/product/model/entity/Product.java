package com.example.ecommerce.product.model.entity;

import com.example.ecommerce.category.model.entity.Category;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String image;
    private String summary;
    private String description;
    private float price;
    private int quantity;
    private String origin;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToMany
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    private String tags;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @PrePersist
    protected  void onCreated(){
        this.created_at =  LocalDateTime.now();
    }
}
