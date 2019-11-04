package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.LocationDTO;
import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.model.Location;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.LocationService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/locations")
public class LocationController {

    @Autowired
    private LocationService service;

    @Autowired
    private UserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<LocationDTO>> getAllFlights() {
        List<Location> locations = service.findAll();
        List<LocationDTO> locationsDTO = new ArrayList<>();

        for (Location l : locations) {
            locationsDTO.add(new LocationDTO(l));
        }

        return new ResponseEntity<>(locationsDTO, HttpStatus.OK);
    }


}
