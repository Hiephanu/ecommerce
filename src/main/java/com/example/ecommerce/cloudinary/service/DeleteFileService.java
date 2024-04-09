package com.example.ecommerce.cloudinary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class DeleteFileService {
    private final Cloudinary cloudinary;
    public Result<Void, DomainException> deleteFile(String publicId){
       try {
           Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
           return Result.success(null);
       } catch (Exception e) {
           return Result.fail(null);
       }
    }
}
