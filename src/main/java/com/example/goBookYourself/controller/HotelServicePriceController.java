package com.example.goBookYourself.controller;


import com.example.goBookYourself.DTO.HotelServicePriceDTO;
import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.model.Hotel;
import com.example.goBookYourself.model.HotelServicePrice;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.HotelPriceService;
import com.example.goBookYourself.service.HotelService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/price")
public class HotelServicePriceController {

    @Autowired
    private HotelPriceService hps;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hot;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<HotelServicePriceDTO>> getHotelAll() {
        List<HotelServicePrice> hotelsPrice = hps.findAll();
        List<HotelServicePriceDTO> hotelPriceDTO = new ArrayList<>();

        for (HotelServicePrice h : hotelsPrice) {
            hotelPriceDTO.add(new HotelServicePriceDTO(h));
        }

        return new ResponseEntity<>(hotelPriceDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/addService",method = RequestMethod.POST)
    public ResponseEntity<Void> addService(HttpServletRequest request, @RequestBody HotelServicePrice hotelService)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("HOTEL_ADMIN")) {
                System.out.println(a.getName());
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Hotel hotel = this.hot.findOne(hotelService.getId());
        System.out.println(hotel.getName());


        Set<HotelServicePrice> service = hotel.getServiceHotel();



        HotelServicePrice hsp = new HotelServicePrice();

        hsp.setName(hotelService.getName());
        hsp.setPrice(hotelService.getPrice());
        hsp.setDiscount(hotelService.getDiscount());

        this.hps.save(hsp);

        service.add(hsp);

        hotel.setServiceHotel(service);
        this.hot.save(hotel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
