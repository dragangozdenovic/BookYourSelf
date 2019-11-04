package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.HotelServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServicePriceRepository extends JpaRepository<HotelServicePrice, Integer> {
    HotelServicePrice findById(int id);
}
