package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Location;
import com.example.goBookYourself.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Location> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void delete(Integer id) {
        repository.removeById(id);
    }

    public Location findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void save(Location location) {
        repository.save(location);
    }
}
