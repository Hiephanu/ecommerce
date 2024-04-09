package com.example.ecommerce.user.service;

import com.example.ecommerce.user.repository.UserRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.auth.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public final Result<User, DomainException> getUserById(UUID uuid){
        Optional<User> user = userRepository.findById(uuid);
        return user.<Result<User, DomainException>>map(Result::success).orElseGet(()->Result.fail(new DomainException("Not found", "NOT_FOUND")));
    }
    public final Result<User, DomainException> saveUser(User user){
        User userResult = userRepository.save(user);
        return Result.success(userResult);
    }
    public final Result<User, DomainException> getUserByEmail(String email){
        Optional<User>  user= userRepository.findByEmail(email);
        return user.<Result<User, DomainException>>map(Result::success).orElseGet(() -> Result.fail(new DomainException("Not found", "NOT_FOUND")));
    }
}
