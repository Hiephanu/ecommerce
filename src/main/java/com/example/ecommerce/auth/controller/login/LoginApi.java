package com.example.ecommerce.auth.controller.login;

import com.example.ecommerce.auth.model.dto.LoginRequest;
import com.example.ecommerce.auth.model.dto.LoginResponse;
import com.example.ecommerce.auth.service.LoginService;
import com.example.ecommerce.share.api.ResponseBody;
import com.example.ecommerce.share.api.ResponseEntityFactory;
import com.example.ecommerce.share.exception.AuthorizeException;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginApi {
    @Autowired
    private LoginService loginService;
    @PostMapping("")
    public ResponseEntity<ResponseBody> login(@RequestBody LoginRequest loginRequest){
        try{
            Result<LoginResponse, DomainException> loginResponse = loginService.login(loginRequest);
            if(loginResponse.isFailed()){
                return ResponseEntityFactory.unauthorized(loginResponse.failedData);
            } else {
                return ResponseEntityFactory.ok(loginResponse.successData);
            }
        } catch (Exception e){
            return ResponseEntityFactory.internalServerError(e);
        }
    }
}
