package com.example.ecommerce.auth.service;

import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.auth.model.entity.Credential;
import com.example.ecommerce.auth.repository.CredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CredentialService {
    private CredentialRepository credentialRepository;
    public Result<Credential, DomainException> saveCredential(Credential credential){
        Credential credentialOptional = credentialRepository.save(credential);
        return Result.success(credentialOptional);
    }
    public Result<Credential, DomainException> getCredentialByUserId(UUID user_id){
        Credential credential = credentialRepository.getCredentialByUserId(user_id);
        if(credential.getId() == null){
            return  Result.fail(new DomainException("Fail to find credential","FAIL_TO_LEAD_CREDENTIAL"));
        }
        else {
            return  Result.success(credential);
        }
    }
}
