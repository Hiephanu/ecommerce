package com.example.ecommerce.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ShopPostDto {
    private String name;
    private String userId;
    private String address;
    private String description;
    private MultipartFile avatar;
}
