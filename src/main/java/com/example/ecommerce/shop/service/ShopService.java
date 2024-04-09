package com.example.ecommerce.shop.service;

import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.cloudinary.model.ImageType;
import com.example.ecommerce.cloudinary.model.entity.Image;
import com.example.ecommerce.cloudinary.service.ImageService;
import com.example.ecommerce.cloudinary.service.UploadService;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.shop.model.dto.ShopPostDto;
import com.example.ecommerce.shop.model.dto.ShopReturnDto;
import com.example.ecommerce.shop.model.entity.Shop;
import com.example.ecommerce.shop.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ImageService imageService;
    private final UploadService uploadService;
    public Result<ShopReturnDto, DomainException> getShopById(UUID id){
        Optional<Shop> shop = shopRepository.findById(id);
        return shop.<Result<ShopReturnDto, DomainException>>map(value -> Result.success(ShopReturnDto.convertToShopReturnDto(value))).orElseGet(() -> Result.fail(new DomainException("NOT_FOUND", "not found")));
    }

    public Result<ShopReturnDto, DomainException> saveShop(ShopPostDto shopPostDto){
        try {
            Result<Map,DomainException> avatar = uploadService.uploadFile(shopPostDto.getAvatar());
            System.out.println(avatar.successData);
            Image image = Image.builder()
                    .publicId(avatar.successData.get("public_id").toString())
                    .imageType(ImageType.AVATAR)
                    .secureUrl(avatar.successData.get("secure_url").toString())
                    .build();
            imageService.saveImage(image);
            Shop shop = Shop.builder()
                    .name(shopPostDto.getName())
                    .address(shopPostDto.getAddress())
                    .user(User.builder().id(UUID.fromString(shopPostDto.getUserId())).build())
                    .avatar(image.getPublicId())
                    .description(shopPostDto.getDescription())
                    .build();
            Shop shopSave = shopRepository.save(shop);
            return Result.success(ShopReturnDto.convertToShopReturnDto(shopSave));
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("FAIL_CREATE_SHOP","fail create shop"));
        }
    }
    public Result<ShopReturnDto, DomainException> updateShop(ShopPostDto shopPostDto){
        try{
            Result<Map, DomainException>  newAvatar =uploadService.uploadFile(shopPostDto.getAvatar());

            String src =newAvatar.successData.get("src").toString();
            Shop shop = Shop.builder()
                    .name(shopPostDto.getName())
                    .address(shopPostDto.getAddress())
                    .user(User.builder().id(UUID.fromString(shopPostDto.getUserId())).build())
                    .description(shopPostDto.getDescription())
                    .build();
        } catch (Exception e){
            return Result.fail(new DomainException("FAIL_TO_UPDATE","fail to update"));
        }
        return null;
    }
}
