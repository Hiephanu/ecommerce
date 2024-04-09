package com.example.ecommerce.shop.api;

import com.example.ecommerce.share.api.ResponseBody;
import com.example.ecommerce.share.api.ResponseEntityFactory;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.shop.model.dto.ShopPostDto;
import com.example.ecommerce.shop.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopCrudApi {
    private final ShopService shopService;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getShopById(@PathVariable UUID id){
        return null;
    }
    @PostMapping("")
    public ResponseEntity<ResponseBody> saveShop(@ModelAttribute ShopPostDto shopPostDto){
        return ResponseEntityFactory.ok(shopService.saveShop(shopPostDto));
    }
    @PutMapping("")
    public ResponseEntity<ResponseBody> updateShop(@RequestBody ShopPostDto shopPostDto){
        return null;
    }
    //role admin of system
    @DeleteMapping("")
    public ResponseEntity<ResponseBody> deleteShop(){
        return  null;
    }
}
