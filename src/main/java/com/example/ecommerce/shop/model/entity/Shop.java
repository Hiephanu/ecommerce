package com.example.ecommerce.shop.model.entity;

import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.category.model.entity.Category;
import com.example.ecommerce.product.model.entity.Product;
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
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar")
    private String avatar;

    @ManyToMany
    @JoinTable(
            name = "shop_category",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    private LocalDateTime created_at;
    @PrePersist
    protected  void onCreated(){
        this.created_at =  LocalDateTime.now();
    }
}
