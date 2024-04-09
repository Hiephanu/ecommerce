package com.example.ecommerce.product.service;

import com.example.ecommerce.cloudinary.model.ImageType;
import com.example.ecommerce.cloudinary.model.entity.Image;
import com.example.ecommerce.cloudinary.service.ImageService;
import com.example.ecommerce.cloudinary.service.UploadService;
import com.example.ecommerce.product.model.dto.ProductDto;
import com.example.ecommerce.product.model.dto.ProductPostDto;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.repository.ProductRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.shop.model.entity.Shop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SaveProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final UploadService uploadService;
    public Result<ProductDto, DomainException> saveProduct(ProductPostDto productPostDto){
        try {
            Result<Map,DomainException> result = uploadService.uploadFile(productPostDto.getImage());
            Image image = Image.builder()
                    .publicId(result.successData.get("public_id").toString())
                    .imageType(ImageType.PRODUCT)
                    .secureUrl(result.successData.get("secure_url").toString())
                    .build();
            imageService.saveImage(image);
            Product product = Product.builder()
                    .image(image.getSecureUrl())
                    .title(productPostDto.getTitle())
                    .summary(productPostDto.getSummary())
                    .description(productPostDto.getDescription())
                    .quantity(productPostDto.getQuantity())
                    .origin(productPostDto.getOrigin())
                    .price(productPostDto.getPrice())
                    .shop(Shop.builder().id(UUID.fromString(productPostDto.getShopId())).build())
                    .categories(productPostDto.getCategories())
                    .tags(productPostDto.getTags())
                    .build();
            Product product1 = productRepository.save(product);
            return  Result.success(ProductDto.convertToProductDto(product1,productPostDto.getCategories()));
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("Internal error","INTERNAL_ERROR"));
        }
    }
}
