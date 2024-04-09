package com.example.ecommerce.auth.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "provider")
    private String provider;

    @Column(name = "hasher")
    private String hash_method;

    @Column(name = "password_hash")
    private String password_hash;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
