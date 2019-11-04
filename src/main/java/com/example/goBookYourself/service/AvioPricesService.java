package com.example.goBookYourself.service;

import com.example.goBookYourself.model.AvioPrices;
import com.example.goBookYourself.repository.AvioPricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AvioPricesService {

    @Autowired
    private AvioPricesRepository repository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void removeById(Integer id){
        repository.removeById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void save(AvioPrices pricing){
        repository.save(pricing);
    }

    public AvioPrices findById(Integer id){
        return repository.findById(id);
    }
}
