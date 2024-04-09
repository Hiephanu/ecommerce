package com.example.ecommerce.auth.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "user_id")
    private UUID user_id;
    @Column(name = "login_at")
    private LocalDateTime login_at;
    @Column(name = "logout_at")
    private LocalDateTime logout_at;
}
