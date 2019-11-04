package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Reservation;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findById(Integer id);
}
