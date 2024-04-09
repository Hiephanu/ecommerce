package com.example.ecommerce.auth.repository;

import com.example.ecommerce.auth.model.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {
    Credential getCredentialByUserId(UUID id);
}
