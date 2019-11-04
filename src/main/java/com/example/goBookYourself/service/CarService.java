package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Car;
import com.example.goBookYourself.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    public List<Car> findAll() {
        return  carRepository.findAll();
    }
}
