package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Reservation;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.model.VerificationToken;
import com.example.goBookYourself.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Reservation save(Reservation reservation) {
        return repository.save(reservation);
    }

    public Reservation findById(Integer id) {
        return repository.findById(id);
    }
}
