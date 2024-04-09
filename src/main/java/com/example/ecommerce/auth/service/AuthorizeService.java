package com.example.ecommerce.auth.service;

import com.example.ecommerce.auth.model.dto.TokenData;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.share.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizeService {
    private final JwtService jwtService;
    public Result<TokenData, DomainException> authorize(String token, Role role){
        Result<TokenData,DomainException> tokenData =  jwtService.decryptToken(token);
        if(tokenData.isFailed()){
            return  Result.fail(new DomainException("UnAuthorize","UNAUTHORIZED"));
        } else {
            if(tokenData.successData.getRole().equals(role.toString())){
                return  Result.success(tokenData.successData);
            } else {
                return Result.fail(new DomainException("access deny","ACCESS_DENY"));
            }
        }
    }
}
