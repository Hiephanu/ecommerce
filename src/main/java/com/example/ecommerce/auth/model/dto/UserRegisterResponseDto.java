package com.example.ecommerce.auth.model.dto;

import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.share.enums.Role;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterResponseDto {
    private UUID id;
    private String mail;
    private String name;
    private Role role;
    public static UserRegisterResponseDto convertToUserResponseDto(User user){
        return UserRegisterResponseDto.builder()
                .id(user.getId())
                .name(user.getEmail())
                .mail(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
