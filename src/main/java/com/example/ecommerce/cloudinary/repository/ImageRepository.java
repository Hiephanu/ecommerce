package com.example.ecommerce.cloudinary.repository;

import com.example.ecommerce.cloudinary.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findBySecureUrl(String secureUrl);
}
