package com.example.goBookYourself.controller;


import com.example.goBookYourself.DTO.HotelReservationDTO;
import com.example.goBookYourself.DTO.HotelServicePriceDTO;
import com.example.goBookYourself.DTO.RoomDTO;
import com.example.goBookYourself.model.HotelReservation;
import com.example.goBookYourself.model.HotelServicePrice;
import com.example.goBookYourself.model.Room;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.HotelPriceService;
import com.example.goBookYourself.service.HotelReservationService;
import com.example.goBookYourself.service.RoomService;
import com.example.goBookYourself.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/hotelReservation")
public class HotelReservationController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelReservationService hrs;
    @Autowired
    private RoomService rs;
    @Autowired
    private HotelPriceService hps;

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public ResponseEntity<List<HotelReservationDTO>> getAllReservation()
    {
        List<HotelReservation> hotelRes = this.hrs.findAll();
        List<HotelReservationDTO> hotelResDTO = new ArrayList<>();

        for(HotelReservation hr : hotelRes)
        {
            hotelResDTO.add(new HotelReservationDTO(hr));
        }

        return new ResponseEntity<>(hotelResDTO,HttpStatus.OK);
    }

    @RequestMapping(value="/addReservation",method = RequestMethod.POST)
    public ResponseEntity<Void> addHotelReservation(HttpServletRequest request, @RequestBody HotelReservationDTO hr)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User me = userService.getUser(username);
        Set<Room> rooms = new HashSet<>();
        Room soba;
        Set<HotelServicePrice> hsp = new HashSet<>();

        if (me == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        HotelReservation res = new HotelReservation();

        for(RoomDTO roomDTO : hr.getRooms())
        {
            soba = rs.findById(roomDTO.getId());
            soba.setReserved(true);
            if(soba!=null)
                this.rs.save(soba);
            rooms.add(soba);
        }
        if(hr.getHotelServicePrice()!=null)
        {
            for(HotelServicePriceDTO hspDTO : hr.getHotelServicePrice()) {
                System.out.println(hps.findById(hspDTO.getId()).getName());
                hsp.add(hps.findById(hspDTO.getId()));
            }
        }

        res.setRooms(rooms);
        res.setFavour(hsp);

        for(Room r : res.getRooms())
        {
            System.out.println(r.getName());
        }

        for(HotelServicePrice hotelServicePrice : res.getFavour())
        {
            System.out.println(hotelServicePrice.getName());
        }

        this.hrs.save(res);

        HotelReservation res1 = this.hrs.findOne(1);

        for(Room r : res1.getRooms())
        {
            System.out.println(r.getName());
        }

        for(HotelServicePrice hotelServicePrice : res1.getFavour())
        {
            System.out.println(hotelServicePrice.getName());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
