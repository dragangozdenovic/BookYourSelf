package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findById(Integer id);
    List<Flight> findByCompany(AvioCompany company);
}
