package com.example.ecommerce.auth.service;

import com.example.ecommerce.auth.model.entity.Session;
import com.example.ecommerce.auth.repository.SessionRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    public Result<Session, DomainException> saveSession(Session session){
        Session sessionSave = sessionRepository.save(session);
        return Result.success(sessionSave);
    }
}
