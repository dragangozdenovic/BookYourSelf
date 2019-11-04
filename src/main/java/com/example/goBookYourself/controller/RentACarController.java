package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.RentACarDTO;
import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.model.RentACar;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.RentACarService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/rent")
public class RentACarController {

    @Autowired
    private RentACarService rentCarService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<RentACarDTO>> getAllRents() {

        List<RentACar> rents = rentCarService.findAll();

        List<RentACarDTO> rentsDTO = new ArrayList<>();

        for (RentACar r : rents) {
            rentsDTO.add(new RentACarDTO(r));
        }

        return new ResponseEntity<>(rentsDTO, HttpStatus.OK);

    }
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResponseEntity<Void> addRenta(HttpServletRequest request, @RequestBody RentACarDTO rentACarDTO)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("ADMIN_ADMIN")) {
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        RentACar rent = new RentACar();
        rent.setName(rentACarDTO.getName());
        rent.setAddress(rentACarDTO.getAddress());
        rent.setPromotingAdd(rentACarDTO.getPromotingAdd());
        rent.setImage("universal.jpg");

        this.rentCarService.save(rent);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}