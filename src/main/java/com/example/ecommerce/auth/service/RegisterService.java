package com.example.ecommerce.auth.service;

import com.example.ecommerce.auth.model.dto.UserRegisterResponseDto;
import com.example.ecommerce.auth.model.dto.UserRegisterRequestDto;
import com.example.ecommerce.auth.model.entity.Credential;
import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegisterService {
    private final UserService userService;

    private final CredentialService credentialService;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;

    public Result<UserRegisterResponseDto, DomainException> register(UserRegisterRequestDto userRegisterRequestDto){
        if(emailService.isValidEmail(userRegisterRequestDto.getEmail())){
            Result<User,DomainException> user = userService.getUserByEmail(userRegisterRequestDto.getEmail());
            if(user.isFailed()){
                User userSave = UserRegisterRequestDto.convertToUser(userRegisterRequestDto);
                Result<User,DomainException> userResult = userService.saveUser(userSave);
                if(userResult.isSuccess) {
                    String hashValue = encoder.encode(userRegisterRequestDto.getPassword());
                    Credential credential = new Credential();
                    credential.setUser(userSave);
                    credential.setProvider("email");
                    credential.setHash_method("bcrypt");
                    credential.setPassword_hash(hashValue);
                    credential.setCreated_at(LocalDateTime.now());
                    Result<Credential,DomainException> credentialResult = credentialService.saveCredential(credential);
                    if(credentialResult.isSuccess){
                        return Result.success(UserRegisterResponseDto.convertToUserResponseDto(userResult.successData));
                    }
                } else {
                    return  Result.fail(new DomainException("Fail to save user","FAIL_TO_SAVE"));
                }
                return  null;
            } else {
                return Result.fail(new DomainException("Account exit","ACCOUNT_EXIT"));
            }
        }
        else {
            return Result.fail(new DomainException("Email invalid","EMAIL_INVALID"));
        }
    }
}
