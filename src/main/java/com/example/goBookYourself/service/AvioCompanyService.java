package com.example.goBookYourself.service;

import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.repository.AvioCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvioCompanyService {

    @Autowired
    private AvioCompanyRepository repository;

    public List<AvioCompany> findAll(){
        return repository.findAll();
    }
    public AvioCompany findByName(String name){
        return repository.findByName(name);
    }
    public AvioCompany save(AvioCompany avioCompany){
        return repository.save(avioCompany);
    }
}
