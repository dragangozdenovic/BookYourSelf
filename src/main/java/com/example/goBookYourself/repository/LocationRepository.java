package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    void removeById(Integer id);
    Location findById(Integer id);
}
