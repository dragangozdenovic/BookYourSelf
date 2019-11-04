package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.BranchOfficeDTO;
import com.example.goBookYourself.model.BranchOffice;
import com.example.goBookYourself.service.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/branches")
public class BranchOfficeController {

    @Autowired
    private BranchOfficeService branchOfficeService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<BranchOfficeDTO>> getAllBrances() {
        List<BranchOffice> branches  = branchOfficeService.findAll();
        List<BranchOfficeDTO> branchesDTO = new ArrayList<>();

        for(BranchOffice b : branches) {
            branchesDTO.add(new BranchOfficeDTO(b));
        }
        return  new ResponseEntity<>(branchesDTO, HttpStatus.OK);
    }
}
