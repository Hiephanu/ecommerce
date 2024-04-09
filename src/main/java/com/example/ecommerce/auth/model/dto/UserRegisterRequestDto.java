package com.example.ecommerce.auth.model.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.example.ecommerce.auth.model.entity.User;
import lombok.Setter;

import java.io.File;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String email;
    private String password;
    private String name;
    private String address;
    private String phone_number;
    private File avatar;
    public static User convertToUser(UserRegisterRequestDto userRegisterRequestDto){
        return  User.builder()
                .email(userRegisterRequestDto.getEmail())
                .name(userRegisterRequestDto.getName())
                .address(userRegisterRequestDto.getAddress())
                .phone_number(userRegisterRequestDto.getPhone_number())
                .build();
    }
}
