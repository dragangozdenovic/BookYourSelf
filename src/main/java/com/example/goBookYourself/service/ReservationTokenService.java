package com.example.goBookYourself.service;

import com.example.goBookYourself.model.ReservationToken;
import com.example.goBookYourself.repository.ReservationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationTokenService {

    @Autowired
    private ReservationTokenRepository repository;

    public ReservationToken save(ReservationToken reservationToken){
        return repository.save(reservationToken);
    }

    public ReservationToken findByToken(String token){
        return repository.findByToken(token);
    }
}
