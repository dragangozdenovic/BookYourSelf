package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Hotel;
import com.example.goBookYourself.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService
{
    @Autowired
    private HotelRepository hotelrepository;

    public Hotel findOne(int id)

    {
        return hotelrepository.getOne(id);
    }

    public Hotel findByName(String name){
        return hotelrepository.findByName(name);
    }

    public List<Hotel> findAll()
    {
        return hotelrepository.findAll();
    }

    public Hotel save(Hotel h)
    {
        return hotelrepository.save(h);
    }
}
