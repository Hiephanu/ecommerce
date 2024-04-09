package com.example.ecommerce.product.service;

import com.example.ecommerce.cloudinary.model.entity.Image;
import com.example.ecommerce.cloudinary.repository.ImageRepository;
import com.example.ecommerce.cloudinary.service.DeleteFileService;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.repository.ProductRepository;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteProductService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final DeleteFileService deleteFileService;
    public void deleteProduct(String productId){
        Optional<Product> product = productRepository.findById(UUID.fromString(productId));
        if(product.isPresent()){
            Image image = imageRepository.findBySecureUrl(product.get().getImage());
            imageRepository.deleteById(image.getPublicId());
            deleteFileService.deleteFile(productId);
            productRepository.deleteById(UUID.fromString(productId));
        }
        Result.success(null);
    }
}
