package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.CarDTO;
import com.example.goBookYourself.model.Car;
import com.example.goBookYourself.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<Car> cars = carService.findAll();
        List<CarDTO> carsDTO = new ArrayList<>();

        for (Car car : cars) {
            carsDTO.add(new CarDTO(car));
        }
        return  new ResponseEntity<>(carsDTO, HttpStatus.OK);
    }
}
