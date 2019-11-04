package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Version
    Long version;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    private AvioCompany company;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "destination")
    private Location destination;

    @OneToMany(mappedBy = "connectingFlight", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Location> connectingCities = new HashSet<>();

    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "arrival_time")
    private Date arrivalTime;

    @Column(name = "flight_time")
    private int flightTime; //in minutes, because professional shit

    @Column(name = "ticket_price")
    private double ticketPrice;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Ticket> tickets = new HashSet<>();

    public Flight(){}

    public Flight(AvioCompany company, Location destination) {
        this.company = company;
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AvioCompany getCompany() {
        return company;
    }

    public void setCompany(AvioCompany company) {
        this.company = company;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Set<Location> getConnectingCities() {
        return connectingCities;
    }

    public void setConnectingCities(Set<Location> connectingCities) {
        this.connectingCities = connectingCities;
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
