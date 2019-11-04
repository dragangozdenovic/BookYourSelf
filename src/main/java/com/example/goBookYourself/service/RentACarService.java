package com.example.goBookYourself.service;

import com.example.goBookYourself.model.RentACar;
import com.example.goBookYourself.repository.RentACarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentACarService {

    @Autowired
    private RentACarRepository rentRepository;

    public List<RentACar> findAll() {
        return rentRepository.findAll();
    }

    public RentACar save(RentACar r) {return rentRepository.save(r);}

    public RentACar findByName(String name) {return rentRepository.findByName(name);}
}
