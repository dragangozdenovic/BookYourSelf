package com.example.goBookYourself.service;

import com.example.goBookYourself.model.VerificationToken;
import com.example.goBookYourself.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationTokenService {

    @Autowired
    VerificationTokenRepository repository;

    public VerificationToken save(VerificationToken token) {
        return repository.save(token);
    }

    public List<VerificationToken> findAll() {
        return repository.findAll();


    }

    public VerificationToken findByToken(String token) {

        return repository.findByToken(token);
    }

    public void deleteByToken(String token){

        repository.deleteByToken(token);
    }

}