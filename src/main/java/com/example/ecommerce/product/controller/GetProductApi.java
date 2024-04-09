package com.example.ecommerce.product.controller;

import com.example.ecommerce.product.model.dto.ProductDetailDto;
import com.example.ecommerce.product.model.dto.ProductDto;
import com.example.ecommerce.product.model.dto.ProductFilterRequest;
import com.example.ecommerce.product.service.GetProductService;
import com.example.ecommerce.share.api.ResponseBody;
import com.example.ecommerce.share.api.ResponseEntityFactory;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class GetProductApi {
    private final GetProductService getProductService;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getProductById(@PathVariable String id){
       try {
           Result<ProductDetailDto, DomainException> product = getProductService.getProductById(id);
            if(product.isFailed()){
                return ResponseEntityFactory.badRequest(product.failedData);
            } else {
                return ResponseEntityFactory.ok(product.successData);
            }
       } catch (Exception e){
           e.printStackTrace();
           return ResponseEntityFactory.internalServerError(e);
       }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseBody> searchProductByName(@RequestParam String keyword,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size){
        try {
            Result<List<ProductDto>,DomainException> products = getProductService.searchProductByName(keyword,page,size);
            if(products.isFailed()){
                return ResponseEntityFactory.badRequest(products.failedData);
            } else {
                return ResponseEntityFactory.ok(products.successData);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntityFactory.internalServerError(e);
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<ResponseBody> filterProduct(@RequestParam(defaultValue = "0") double minPrice,
                                                      @RequestParam double maxPrice,
                                                      @RequestParam String origin,
                                                      @RequestParam int quantity,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "20") int size){
        try {
            ProductFilterRequest productFilterRequest = ProductFilterRequest.builder()
                    .maxPrice(maxPrice)
                    .minPrice(minPrice)
                    .origin(origin)
                    .quantity(quantity)
                    .build();

            Result<List<ProductDto>,DomainException> productDtos = getProductService.filterProduct(productFilterRequest,page,size);
            if(productDtos.isFailed()){
                return ResponseEntityFactory.badRequest(productDtos.failedData);
            } else {
                return ResponseEntityFactory.ok(productDtos.successData);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntityFactory.internalServerError(e);
        }
    }

}
