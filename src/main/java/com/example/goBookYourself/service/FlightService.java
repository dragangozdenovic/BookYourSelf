package com.example.goBookYourself.service;

import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.Flight;
import com.example.goBookYourself.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository repository;

    public List<Flight> findAll() {
        return repository.findAll();
    }

    public Flight findById(Integer id){
        return repository.findById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void save(Flight flight){
        repository.save(flight);
    }

    public List<Flight> findByCompany(AvioCompany company){
        return repository.findByCompany(company);
    }
}
