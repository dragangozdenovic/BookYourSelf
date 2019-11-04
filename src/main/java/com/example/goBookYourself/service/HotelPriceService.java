package com.example.goBookYourself.service;

import com.example.goBookYourself.model.HotelServicePrice;
import com.example.goBookYourself.repository.HotelServicePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelPriceService {

    @Autowired
    private HotelServicePriceRepository hotelServiceRepository;

    public HotelServicePrice findById(int id)

    {
        return hotelServiceRepository.findById(id);
    }

    public List<HotelServicePrice> findAll()
    {
        return hotelServiceRepository.findAll();
    }

    public HotelServicePrice save(HotelServicePrice h)
    {
        return hotelServiceRepository.save(h);
    }
}
