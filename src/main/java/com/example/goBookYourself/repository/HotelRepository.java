package com.example.goBookYourself.repository;


import com.example.goBookYourself.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Hotel findByName(String name);
}
