package com.example.goBookYourself.service;

import com.example.goBookYourself.model.HotelReservation;
import com.example.goBookYourself.repository.HotelReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelReservationService {

    @Autowired
    private HotelReservationRepository hrs;

    public HotelReservation findOne(int id){return hrs.getOne(id);}

    public List<HotelReservation> findAll()
    {
        return hrs.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public HotelReservation save(HotelReservation h)
    {
        return hrs.save(h);
    }
}
