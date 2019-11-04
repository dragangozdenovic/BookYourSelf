package com.example.goBookYourself.service;

import com.example.goBookYourself.model.CarRating;
import com.example.goBookYourself.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    public List<CarRating> findAll() { return  ratingRepository.findAll();}
}
