package com.example.ecommerce.cloudinary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class UploadService {
    @Autowired
    private final Cloudinary cloudinary;

    public Result<Map, DomainException> uploadFile(MultipartFile file) throws IOException {
        byte[] fileByte =file.getBytes();
        return Result.success(cloudinary.uploader().upload(fileByte, ObjectUtils.emptyMap()));
    }
}
