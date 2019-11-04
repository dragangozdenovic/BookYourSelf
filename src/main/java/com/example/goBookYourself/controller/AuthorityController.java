package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.AuthorityDTO;
import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authService;

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public ResponseEntity<List<AuthorityDTO>> getAllAuthority()
    {
        List<Authority> auth = this.authService.findAll();
        List<AuthorityDTO> authDTO = new ArrayList<>();

        for(Authority a : auth)
        {
            authDTO.add(new AuthorityDTO(a));
        }

        return new ResponseEntity<>(authDTO,HttpStatus.OK);
    }
}
