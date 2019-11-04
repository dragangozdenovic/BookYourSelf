package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelReservationRepository extends JpaRepository<HotelReservation,Integer> {
}
