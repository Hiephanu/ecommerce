package com.example.ecommerce.product.controller;

import com.example.ecommerce.auth.model.dto.TokenData;
import com.example.ecommerce.auth.service.AuthorizeService;
import com.example.ecommerce.product.service.DeleteProductService;
import com.example.ecommerce.share.api.ResponseBody;
import com.example.ecommerce.share.api.ResponseEntityFactory;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.share.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class DeleteProductApi {
    private final DeleteProductService deleteProductService;
    private final AuthorizeService authorizeService;
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteProduct(@RequestHeader String token, @PathVariable String id){
        try {
            Result<TokenData, DomainException> tokenData = authorizeService.authorize(token, Role.ADMIN_SHOP);
            if(tokenData.isFailed()){
                return ResponseEntityFactory.unauthorized(tokenData.failedData);
            } else {
                deleteProductService.deleteProduct(id);
                return ResponseEntityFactory.ok(null);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntityFactory.internalServerError(e);
        }
    }
}
