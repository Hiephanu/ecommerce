package com.example.ecommerce.voucher.service;

import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.voucher.model.entity.Voucher;

public class CheckConditionService {
    public Result<Boolean, DomainException> checkByPrice(double totalPrice, Voucher voucher){
        if(totalPrice > voucher.getRequire()){
            return Result.success(true);
        } else {
            return Result.success(false);
        }
    }
}
