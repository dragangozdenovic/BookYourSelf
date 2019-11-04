package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.HotelDTO;
import com.example.goBookYourself.DTO.RoomDTO;
import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.model.Hotel;
import com.example.goBookYourself.model.Room;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.HotelService;
import com.example.goBookYourself.service.RoomService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<HotelDTO>> getHotelAll() {
        List<Hotel> hotels = hotelService.findAll();
        List<HotelDTO> hotelDTO = new ArrayList<>();

        for (Hotel h : hotels) {
            hotelDTO.add(new HotelDTO(h));
        }

        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/changeHotel",method = RequestMethod.PUT)
    public ResponseEntity<HotelDTO> changeHotel(HttpServletRequest request,@RequestBody HotelDTO hotel)
    {
        String token = this.tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("HOTEL_ADMIN")) {
                System.out.println(a.getName());
                validAuthority = true;
                break;
            }
        if (!validAuthority) {
            System.out.println("IZBACIO 1");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else if (!(admin.getAdmin_hotel().getId()==(hotel.getId()))) {
            System.out.println("IZBACIO 2");
            System.out.println(admin.getAdmin_hotel().getId());
            System.out.println(hotel.getId());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Hotel hot = hotelService.findByName(hotel.getName());

        hot.setName(hotel.getName());
        hot.setAddress(hotel.getAddress());
        hot.setPromoDescription(hotel.getPromoDescription());

        hotelService.save(hot);

        return new ResponseEntity<>(new HotelDTO(hot),HttpStatus.OK);
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResponseEntity<Void> addHotel(HttpServletRequest request,@RequestBody HotelDTO hotelDTO)
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

        Hotel hot = new Hotel();
        hot.setName(hotelDTO.getName());
        hot.setAddress(hotelDTO.getAddress());
        hot.setPromoDescription(hotelDTO.getPromoDescription());
        hot.setImage("universal.jpeg");

        this.hotelService.save(hot);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
