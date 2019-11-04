package com.example.goBookYourself.controller;

import com.example.goBookYourself.DTO.FlightDTO;
import com.example.goBookYourself.DTO.LocationDTO;
import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.Flight;
import com.example.goBookYourself.model.Location;
import com.example.goBookYourself.model.User;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.AvioCompanyService;
import com.example.goBookYourself.service.FlightService;
import com.example.goBookYourself.service.LocationService;
import com.example.goBookYourself.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/flights")
public class FlightController {

    @Autowired
    private FlightService service;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AvioCompanyService avioCompanyService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        List<Flight> flights = service.findAll();
        List<FlightDTO> flightsDTO = new ArrayList<>();

        for (Flight f : flights) {
            flightsDTO.add(new FlightDTO(f));
        }

        return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/allOur", method = RequestMethod.GET)
    public ResponseEntity<List<FlightDTO>> getOurFlights(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);

        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioCompanyService.findByName(admin.getAvioCompany().getName());

        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<FlightDTO> flightsDTO = new ArrayList<>();


        for (Flight flight : service.findByCompany(avioCompany))
            flightsDTO.add(new FlightDTO(flight));

        return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> addFlight(HttpServletRequest request, @RequestBody FlightDTO flightDTO) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);

        User admin = userService.getUser(username);


        if (admin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvioCompany avioCompany = avioCompanyService.findByName(admin.getAvioCompany().getName());


        if (avioCompany == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Flight flight = new Flight();
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setCompany(avioCompany);
        flight.setFlightTime(flightDTO.getFlightTime());
        flight.setTicketPrice(flightDTO.getTicketPrice());
        flight.setDestination(locationService.findById(flightDTO.getLocationDTO().getId()));


        for (LocationDTO locationDTO : flightDTO.getConnectedCities())
            flight.getConnectingCities().add(locationService.findById(locationDTO.getId()));


        service.save(flight);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
