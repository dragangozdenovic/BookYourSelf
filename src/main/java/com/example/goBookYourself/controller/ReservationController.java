package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.ReservationDTO;
import com.example.goBookYourself.DTO.TicketDTO;
import com.example.goBookYourself.model.*;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/reservations")
public class ReservationController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService service;

    @Autowired
    EmailService emailService;

    @Autowired
    private ReservationTokenService reservationTokenService;

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public ResponseEntity<List<ReservationDTO>> getAllReservation()
    {
        List<Reservation> res = this.service.findAll();
        List<ReservationDTO> resDTO = new ArrayList<>();

        for(Reservation r : res)
        {
            resDTO.add(new ReservationDTO(r));
        }

        return new ResponseEntity<>(resDTO,HttpStatus.OK);
    }

    @RequestMapping(value = "/reserveFlight", method = RequestMethod.POST)
    public ResponseEntity<Void> reserveFlight(HttpServletRequest request, @RequestBody Set<TicketDTO> ticketsDTO) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User me = userService.getUser(username);
        Set<Ticket> tickets = new HashSet<>();

        if (me == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        for (TicketDTO ticketDTO : ticketsDTO) {
            System.out.println("CCCCCCCCCCCCCC " + ticketDTO.getPassengerUsername());
            Ticket ticket = ticketService.findById(ticketDTO.getId());
            if (ticket == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            ticket.setPassengerLastName(ticketDTO.getPassengerLastName());
            ticket.setPassengerName(ticketDTO.getPassengerName());
            ticket.setPassengerUsername(ticketDTO.getPassengerUsername());
            ticket.setPassportNumber(ticketDTO.getPassportNumber());
            ticket.setReserved(true);

            tickets.add(ticket);
        }

        Reservation reservation = new Reservation();

        for (Ticket ticket : tickets) {
            ticketService.save(ticket);
            reservation.getTickets().add(ticket);
        }

        System.out.println("RESERVATION TICKET SIZE " + reservation.getTickets().size());
        service.save(reservation);

        try {
            emailService.sendMailFlightReservation(me, reservation);
        } catch (Exception e) {
            System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
        }

        for (Ticket ticket : reservation.getTickets()) {
            if (ticket.getPassengerUsername() != null)
                if (!ticket.getPassengerUsername().equals(username)) {
                    System.out.println("passenger username " + ticket.getPassengerUsername());
                    System.out.println("passenger ticket size " + reservation.getTickets().size());
                    acceptReservation(reservation.getId(), ticket.getPassengerUsername());
                }
        }
        Reservation res = service.findById(reservation.getId());
        System.out.println("NANIIIIIII " + res.getTickets().size());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public void acceptReservation(Integer reservationId, String username) {

        User user = userService.getUser(username);

        if (user == null)
            return;

        System.out.println("BBBBBB " + username);

        String uuid = UUID.randomUUID().toString();

        ReservationToken token = new ReservationToken();
        token.setReservation(reservationId);
        token.setToken(uuid);
        token.setUsername(username);

        reservationTokenService.save(token);

        ReservationToken token1 = reservationTokenService.findByToken(token.getToken());
        Reservation reservation = service.findById(token1.getReservation());


        System.out.println("NANIIIII OPET : " + reservation.getTickets().size());

        try {
            emailService.sendMailFlightReservationAccept(user, reservation, uuid);
        } catch (Exception e) {
            System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/rejectReservation", method = RequestMethod.GET)
    public ResponseEntity<Void> rejectReservation(String token){
        ReservationToken reservationToken = reservationTokenService.findByToken(token);
        String username = reservationToken.getUsername();

        System.out.println("aj molim te " + token);
        System.out.println("gggggg " + reservationToken.getReservation());

        Reservation reservation = service.findById(reservationToken.getReservation());

        System.out.println("AAAAAAAAAAAAAAAAAAA " + reservation.getTickets().size());

        Ticket friendTicket;

        for(Ticket ticket : reservation.getTickets()) {
            System.out.println(ticket.getPassengerUsername());
            System.out.println("USERNAME " + username);
            if (ticket.getPassengerUsername().equals(username)) {
                System.out.println("USAO OVDE");
                friendTicket = ticket;
                friendTicket.setPassengerLastName(null);
                friendTicket.setPassengerName(null);
                friendTicket.setReserved(false);
                friendTicket.setPassengerUsername(null);

                ticketService.save(friendTicket);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
