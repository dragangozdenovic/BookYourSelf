package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.TicketDTO;
import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.Flight;
import com.example.goBookYourself.model.Ticket;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.AvioCompanyService;
import com.example.goBookYourself.service.FlightService;
import com.example.goBookYourself.service.TicketService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AvioCompanyService avioService;

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<Ticket> tickets = service.findAll();
        List<TicketDTO> ticketsDTO = new ArrayList<>();

        for (Ticket t : tickets) {
            ticketsDTO.add(new TicketDTO(t));
        }

        return new ResponseEntity<>(ticketsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> removeTicket(HttpServletRequest request, @RequestBody TicketDTO ticketDTO) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);
        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioService.findByName(admin.getAvioCompany().getName());


        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Ticket ticket = new Ticket();


        Flight flight = flightService.findById(ticketDTO.getFlight().getId());

        ticket.setFastReservationDiscount(ticketDTO.getFastReservationDiscount());
        ticket.setSeatNumber(ticketDTO.getSeatNumber());
        ticket.setReserved(false);
        ticket.setFlight(flight);

        service.save(ticket);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public ResponseEntity<Void> removeTicket(HttpServletRequest request, @RequestBody int id) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);


        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioService.findByName(admin.getAvioCompany().getName());

        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


        service.removeById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public ResponseEntity<Void> changeTicket(HttpServletRequest request, @RequestBody TicketDTO ticketDTO) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);
        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioService.findByName(admin.getAvioCompany().getName());

        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Ticket ticket = service.findById(ticketDTO.getId());
        ticket.setSeatNumber(ticketDTO.getSeatNumber());
        ticket.setFastReservationDiscount(ticketDTO.getFastReservationDiscount());
        service.save(ticket);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
