package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.CarRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<CarRating,Long> {
}
