package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.AvioCompanyDTO;
import com.example.goBookYourself.DTO.LocationDTO;
import com.example.goBookYourself.DTO.TicketDTO;
import com.example.goBookYourself.model.*;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.AvioCompanyService;
import com.example.goBookYourself.service.LocationService;
import com.example.goBookYourself.service.TicketService;
import com.example.goBookYourself.service.UserService;
import jdk.internal.org.objectweb.asm.tree.LocalVariableAnnotationNode;
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
@RequestMapping(value = "api/aviocompanies")
public class AvioCompanyController {

    @Autowired
    private AvioCompanyService service;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<AvioCompanyDTO>> getAllAvioCompanies() {
        List<AvioCompany> companies = service.findAll();
        List<AvioCompanyDTO> companiesDTO = new ArrayList<>();

        for (AvioCompany ac : companies) {
            companiesDTO.add(new AvioCompanyDTO(ac));
        }

        return new ResponseEntity<>(companiesDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/allOurTickets", method = RequestMethod.GET)
    public ResponseEntity<List<TicketDTO>> getAllOurTickets(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = service.findByName(admin.getAvioCompany().getName());


        List<Ticket> allTickets = ticketService.findAll();
        List<TicketDTO> tickets = new ArrayList<>();


        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (Ticket ticket : allTickets)
            for (Flight flight : avioCompany.getFlights())
                if (ticket.getFlight().getId().equals(flight.getId())) {
                    if (!ticket.isReserved())
                        tickets.add(new TicketDTO(ticket));
                    break;
                }

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/allOurTicketsAll", method = RequestMethod.GET)
    public ResponseEntity<List<TicketDTO>> getAllOurTicketsAll(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = service.findByName(admin.getAvioCompany().getName());


        List<Ticket> allTickets = ticketService.findAll();
        List<TicketDTO> tickets = new ArrayList<>();


        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (Ticket ticket : allTickets)
            for (Flight flight : avioCompany.getFlights())
                if (ticket.getFlight().getId().equals(flight.getId())) {
                    tickets.add(new TicketDTO(ticket));
                }

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/allNotMine", method = RequestMethod.GET)
    public ResponseEntity<List<LocationDTO>> getAllAvioCompaniesNotMine(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = service.findByName(admin.getAvioCompany().getName());


        List<Location> locations = locationService.findAll();
        List<LocationDTO> locationsDTO = new ArrayList<>();


        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("AVIO_ADMIN")) {
                System.out.println(a.getName());
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        for (Location location : locations)
            if (!avioCompany.getLocations().contains(location))
                locationsDTO.add(new LocationDTO(location));


        return new ResponseEntity<>(locationsDTO, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public ResponseEntity<Void> removeLocation(HttpServletRequest request, @RequestBody LocationDTO locationDTO) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Location location = locationService.findById(locationDTO.getId());
        if (location == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        AvioCompany avioCompany = service.findByName(admin.getAvioCompany().getName());

        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("AVIO_ADMIN")) {
                System.out.println(a.getName());
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        avioCompany.getLocations().remove(location);

        service.save(avioCompany);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/addToAvio", method = RequestMethod.PUT)
    public ResponseEntity<Void> addLocation(HttpServletRequest request, @RequestBody LocationDTO locationDTO) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Location location = locationService.findById(locationDTO.getId());

        if (location == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        AvioCompany avioCompany = service.findByName(admin.getAvioCompany().getName());


        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        boolean validAuthority = false;

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("AVIO_ADMIN")) {
                System.out.println(a.getName());
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        avioCompany.getLocations().add(location);

        service.save(avioCompany);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/changeAvio", method = RequestMethod.PUT)
    public ResponseEntity<AvioCompanyDTO> changeAvioInfo(HttpServletRequest request, @RequestBody AvioCompanyDTO avioCompanyDTO) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        boolean validAuthority = false;

        if(admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        for (Authority a : admin.getAuthorities())
            if (a.getName().equals("AVIO_ADMIN")) {
                System.out.println(a.getName());
                validAuthority = true;
                break;
            }

        if (!validAuthority) {
            System.out.println("IZBACIO 1");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else if (!admin.getAvioCompany().getId().equals(avioCompanyDTO.getId())) {
            System.out.println("IZBACIO 2");
            System.out.println(admin.getAvioCompany().getId());
            System.out.println(avioCompanyDTO.getId());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        AvioCompany avioCompany = service.findByName(avioCompanyDTO.getName());
        avioCompany.setName(avioCompanyDTO.getName());
        avioCompany.setAddress(avioCompanyDTO.getAddress());
        avioCompany.setPromoDescription(avioCompanyDTO.getPromoDescription());

        service.save(avioCompany);

        return new ResponseEntity<>(new AvioCompanyDTO(avioCompany), HttpStatus.OK);
    }
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResponseEntity<Void> addAvio(HttpServletRequest request, @RequestBody AvioCompanyDTO avioCompanyDTO)
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

        AvioCompany avio = new AvioCompany();
        avio.setName(avioCompanyDTO.getName());
        avio.setAddress(avioCompanyDTO.getAddress());
        avio.setPromoDescription(avioCompanyDTO.getPromoDescription());
        avio.setImage("universal.jpg");
        this.service.save(avio);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
