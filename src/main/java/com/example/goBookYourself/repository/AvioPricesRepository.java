package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.AvioPrices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvioPricesRepository extends JpaRepository<AvioPrices, Long> {

    void removeById(Integer id);

    AvioPrices findById(Integer id);
}
