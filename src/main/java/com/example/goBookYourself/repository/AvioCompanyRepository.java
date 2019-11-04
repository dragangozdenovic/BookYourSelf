package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.AvioCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvioCompanyRepository extends JpaRepository<AvioCompany, Long> {
    AvioCompany findByName(String name);
}
