package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findAll() {return authorityRepository.findAll();}

    public Authority findByName(String name) {return authorityRepository.findByName(name);}
}
