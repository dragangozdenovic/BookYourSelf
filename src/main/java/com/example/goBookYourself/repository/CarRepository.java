package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
