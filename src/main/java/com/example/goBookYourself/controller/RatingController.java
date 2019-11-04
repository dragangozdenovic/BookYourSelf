package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.CarRatingDTO;
import com.example.goBookYourself.model.CarRating;
import com.example.goBookYourself.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/rating")
public class RatingController {

    private RatingService ratingService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<CarRatingDTO>> getAllRatings() {
        List<CarRating> rates = ratingService.findAll();
        List<CarRatingDTO> ratesDTO = new ArrayList<>();

        for(CarRating rat: rates) {
            ratesDTO.add(new CarRatingDTO(rat));
        }
        return  new ResponseEntity<>(ratesDTO, HttpStatus.OK);
    }
}
