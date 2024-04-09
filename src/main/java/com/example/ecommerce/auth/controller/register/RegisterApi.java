package com.example.ecommerce.auth.controller.register;

import com.example.ecommerce.auth.model.dto.UserRegisterRequestDto;
import com.example.ecommerce.auth.model.dto.UserRegisterResponseDto;
import com.example.ecommerce.auth.service.RegisterService;
import com.example.ecommerce.share.api.ResponseBody;
import com.example.ecommerce.share.api.ResponseEntityFactory;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterApi {

    @Autowired
    private final RegisterService registerService;

    @PostMapping
    public final ResponseEntity<ResponseBody> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        try{
            Result<UserRegisterResponseDto,DomainException> registerResponseDto = registerService.register(userRegisterRequestDto);
            if(registerResponseDto.isFailed()){
                return ResponseEntityFactory.badRequest(registerResponseDto.failedData);
            } else {
                return  ResponseEntityFactory.ok(registerResponseDto.successData);
            }
        } catch (Exception e){
            return ResponseEntityFactory.internalServerError(e);
        }
    }
}
