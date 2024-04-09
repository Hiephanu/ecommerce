package com.example.ecommerce.auth.model.entity;

import com.example.ecommerce.share.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "slug")
    private String slug;
    @Column(name = "role")
    private Role role;
    @Column(name = "name")
    private String name;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "email")
    private String email;
    @Column(name = "email_validate_at")
    private LocalDateTime email_validate_at;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "phone_validate_at")
    private String phone_validate_at;
    @Column(name = "address")
    private String address;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
    @PrePersist
    protected  void onCreated(){
        this.created_at =  LocalDateTime.now();
        this.role = Role.CUSTOMER;
        this.email_validate_at = LocalDateTime.now();
    }
}
