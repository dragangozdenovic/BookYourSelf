package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Reservation;
import com.example.goBookYourself.model.ReservationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTokenRepository extends JpaRepository<ReservationToken, Long> {

    ReservationToken findByToken(String token);
}
