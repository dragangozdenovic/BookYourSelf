package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentACarRepository extends JpaRepository<RentACar,Integer> {

    RentACar findByName(String name);
}
