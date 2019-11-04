package com.example.goBookYourself.service;

import com.example.goBookYourself.model.BranchOffice;
import com.example.goBookYourself.repository.BranchOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchOfficeService {
    @Autowired
    private BranchOfficeRepository branchOfficeRepository;
    public List<BranchOffice> findAll() {return branchOfficeRepository.findAll();}

}
