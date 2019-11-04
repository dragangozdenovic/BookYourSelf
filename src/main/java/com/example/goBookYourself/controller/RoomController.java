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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/all" ,method = RequestMethod.GET)
    public ResponseEntity<List<RoomDTO>> getRooms()
    {
        List<Room> rooms = roomService.findAll();
        List<RoomDTO> roomsDTO = new ArrayList<>();
        for(Room s : rooms)
        {
            roomsDTO.add(new RoomDTO(s));
        }

        return new ResponseEntity<>(roomsDTO,HttpStatus.OK);
    }
    @Transactional
    @RequestMapping(value = "/deleteRoom",method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteRoom(HttpServletRequest request, @RequestBody RoomDTO roomDTO)
    {
        System.out.println(roomDTO.getId());

        String token = tokenUtils.getToken(request);

        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        Hotel hot = hotelService.findByName(admin.getAdmin_hotel().getName());

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        if(hot == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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

        this.roomService.removeById(roomDTO.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/editRoom",method = RequestMethod.PUT)
    public ResponseEntity<Void> editRoom(HttpServletRequest request,@RequestBody RoomDTO roomDTO)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Hotel hot = hotelService.findByName(admin.getAdmin_hotel().getName());

        if(hot == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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

        Room room = this.roomService.findById(roomDTO.getId());

        room.setName(roomDTO.getName());
        room.setNumber_of_room(roomDTO.getNumber_of_room());
        room.setDate_in(roomDTO.getDate_in());
        room.setDate_out(roomDTO.getDate_out());
        room.setPrice(roomDTO.getPrice());
        room.setNumber_bed(roomDTO.getNumber_bed());
        room.setNumber_floor(roomDTO.getNumber_floor());

        this.roomService.save(room);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value="/addRoom",method = RequestMethod.POST)
    public ResponseEntity<Void> addRoom(HttpServletRequest request,@RequestBody RoomDTO roomDTO)
    {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Hotel hot = hotelService.findByName(admin.getAdmin_hotel().getName());

        if(hot == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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


        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setDate_in(roomDTO.getDate_in());
        room.setDate_out(roomDTO.getDate_out());
        room.setPrice(roomDTO.getPrice());
        room.setNumber_of_room(roomDTO.getNumber_of_room());
        room.setNumber_bed(roomDTO.getNumber_bed());
        room.setNumber_floor(roomDTO.getNumber_floor());

        if(hot.getId()==roomDTO.getHot())
            room.setHotel(hot);

        this.roomService.save(room);

        return new ResponseEntity<>(HttpStatus.OK);

    }



}
