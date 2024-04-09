package com.example.ecommerce.cloudinary.service;

import com.example.ecommerce.cloudinary.model.entity.Image;
import com.example.ecommerce.cloudinary.repository.ImageRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    public Result<Image, DomainException> getImageByPublicId(String publicId){
        Optional<Image> image = imageRepository.findById(publicId);
        return image.<Result<Image, DomainException>>map(Result::success).orElseGet(() -> Result.fail(new DomainException("NOT_FOUND", "not found")));
    }
    public Result<Image,DomainException> saveImage(Image image){
        Image  imageSave =  imageRepository.save(image);
        if(imageSave.getPublicId() != null){
            return Result.success(image);
        } else {
            return Result.fail(new DomainException("Fail_TO_SAVE","fail to save"));
        }
    }
}
