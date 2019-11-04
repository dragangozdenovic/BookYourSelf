package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.Flight;
import com.example.goBookYourself.model.Location;
import com.example.goBookYourself.model.Ticket;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FlightDTO {

    private Integer id;
    private Date departureTime;
    private Date arrivalTime;
    private int flightTime; //in minutes, because professional shit
    private double ticketPrice;
    private Set<LocationDTO> connectedCities = new HashSet<>();
    private LocationDTO locationDTO;
    private AvioCompanyDTO companyDTO;
    private Long version;

    public FlightDTO() {

    }


    public FlightDTO(Integer id, Date departureTime, Date arrivalTime, int flightTime, double ticketPrice,
                     AvioCompany company, Set<Location> connectingCities, Location destination, Long version) {

        this.id = id;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightTime = flightTime;
        this.ticketPrice = ticketPrice;
        this.companyDTO = new AvioCompanyDTO(company);
        this.version = version;

        for (Location location : connectingCities)
            this.connectedCities.add(new LocationDTO(location));

        this.locationDTO = new LocationDTO(destination);

    }

    public FlightDTO(Flight flight) {
        this(flight.getId(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getFlightTime(),
                flight.getTicketPrice(), flight.getCompany(), flight.getConnectingCities(),
                flight.getDestination(), flight.getVersion());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Set<LocationDTO> getConnectedCities() {
        return connectedCities;
    }

    public void setConnectedCities(Set<LocationDTO> connectedCities) {
        this.connectedCities = connectedCities;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public AvioCompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(AvioCompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
