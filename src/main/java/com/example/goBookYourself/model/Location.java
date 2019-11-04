package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "city", "country"})})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Version
    Long version;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "connecting_flight")
    private Flight connectingFlight;

    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Flight> flightsDestination = new HashSet<>();

    @Column(name = "airport", nullable = false, length = 25)
    private String airport;

    @Column(name = "city", nullable = false, length = 25)
    private String city;

    @Column(name = "country", nullable = false, length = 25)
    private String country;

    public Location(){}

    public Location(Integer id){
        this.id = id;
    }

    public Location(String airport, String city, String country) {
        this.airport = airport;
        this.city = city;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Flight getConnectingFlight() {
        return connectingFlight;
    }

    public void setConnectingFlight(Flight connectingFlight) {
        this.connectingFlight = connectingFlight;
    }

    public Set<Flight> getFlightsDestination() {
        return flightsDestination;
    }

    public void setFlightsDestination(Set<Flight> flightsDestination) {
        this.flightsDestination = flightsDestination;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
