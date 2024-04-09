package com.example.ecommerce.auth.service;

import com.example.ecommerce.auth.model.dto.TokenData;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.share.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    private static final String secret_key  = "secret_key";
    private static final long EXPIRATION_TIME = 1;
    public Result<String, DomainException> generateToken(UUID user_id, Role role){
        Date nowDate = new Date();
        Date expiryDate = new Date(nowDate.getTime() + EXPIRATION_TIME);
        return Result.success(Jwts.builder()
                .claim("user_id", user_id.toString())
                .claim("role",role.toString())
                .setIssuedAt(nowDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret_key.getBytes())
                .compact());
    }
    public Result<TokenData, DomainException> decryptToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJwt(token).getBody();
            TokenData tokenData = TokenData.builder()
                    .user_id(claims.get("user_id").toString())
                    .role(claims.get("role").toString())
                    .build();
            return Result.success(tokenData);
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("Fail verify token","FAIL_VERIFY_TOKEN"));
        }
    }
}
