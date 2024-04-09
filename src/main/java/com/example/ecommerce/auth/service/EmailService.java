package com.example.ecommerce.auth.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public boolean isValidEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }
}
