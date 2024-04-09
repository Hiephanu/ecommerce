package com.example.ecommerce.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private UUID user_id;
    private String username;
    private String avatar;
    private String token;
}
