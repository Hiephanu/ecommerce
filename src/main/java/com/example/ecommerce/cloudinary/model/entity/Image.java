package com.example.ecommerce.cloudinary.model.entity;

import com.example.ecommerce.cloudinary.model.ImageType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Image {
    @Id
    private String publicId;
    private ImageType imageType;
    private String secureUrl;
    private LocalDateTime createdAt;
    @PrePersist
    protected  void onCreated(){
        this.createdAt =  LocalDateTime.now();
    }
}
