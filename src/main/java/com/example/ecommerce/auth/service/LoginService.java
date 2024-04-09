package com.example.ecommerce.auth.service;

import com.example.ecommerce.auth.model.dto.LoginRequest;
import com.example.ecommerce.auth.model.dto.LoginResponse;
import com.example.ecommerce.auth.model.entity.Credential;
import com.example.ecommerce.auth.model.entity.Session;
import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.share.exception.AuthorizeException;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.exception.NotFoundException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserService userService;
    private final CredentialService credentialService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SessionService sessionService;
    private final JwtService jwtService;
    public Result<LoginResponse, DomainException> login(LoginRequest loginRequest){
        Result<User,DomainException> userResult =userService.getUserByEmail(loginRequest.getEmail());
        if(userResult.isFailed()){
            return Result.fail(new NotFoundException(userResult.failedData.getMessage()));
        } else {
            Result<Credential, DomainException> credentialResult = credentialService.getCredentialByUserId(userResult.successData.getId());
            if(bCryptPasswordEncoder.matches(loginRequest.getPassword(),credentialResult.successData.getPassword_hash())){
                Session session = Session.builder()
                        .user_id(userResult.successData.getId())
                        .login_at(LocalDateTime.now())
                        .logout_at(null)
                        .build();
                Result<Session,DomainException> sessionResult = sessionService.saveSession(session);
                Result<String,DomainException> token = jwtService.generateToken(userResult.successData.getId(), userResult.successData.getRole());
                LoginResponse loginResponse = LoginResponse.builder()
                        .user_id(userResult.successData.getId())
                        .token(token.successData)
                        .username(userResult.successData.getName())
                        .avatar(userResult.successData.getAvatar())
                        .build();
                return Result.success(loginResponse);
            } else {
                return Result.fail(new AuthorizeException("Wrong password"));
            }
        }
    }
}
