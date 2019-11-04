package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    void deleteByToken(String token);
}
