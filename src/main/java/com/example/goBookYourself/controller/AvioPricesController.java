package com.example.goBookYourself.controller;


import com.example.goBookYourself.DTO.AvioPricesDTO;
import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.AvioPrices;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.AvioCompanyService;
import com.example.goBookYourself.service.AvioPricesService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/avioprices")
public class AvioPricesController {

    @Autowired
    private AvioPricesService service;

    @Autowired
    private AvioCompanyService avioCompanyService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Transactional
    @RequestMapping(value = "remove", method = RequestMethod.PUT)
    public ResponseEntity<Void> removePricing(HttpServletRequest request, @RequestBody AvioPricesDTO avioPricesDTO) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioCompanyService.findByName(admin.getAvioCompany().getName());

        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        AvioPrices pricing = service.findById(avioPricesDTO.getId());

        if (pricing == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        service.removeById(pricing.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "change", method = RequestMethod.PUT)
    public ResponseEntity<Void> changePricing(HttpServletRequest request, @RequestBody AvioPricesDTO avioPricesDTO) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioCompanyService.findByName(admin.getAvioCompany().getName());

        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        AvioPrices pricing = service.findById(avioPricesDTO.getId());

        if (pricing == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        pricing.setName(avioPricesDTO.getName());
        pricing.setPrice(avioPricesDTO.getPrice());

        service.save(pricing);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Void> addPricing(HttpServletRequest request, @RequestBody AvioPricesDTO avioPricesDTO) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioCompanyService.findByName(admin.getAvioCompany().getName());


        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        AvioPrices pricing = new AvioPrices();
        pricing.setAvioCompany(avioCompany);
        pricing.setName(avioPricesDTO.getName());
        pricing.setPrice(avioPricesDTO.getPrice());

        service.save(pricing);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
