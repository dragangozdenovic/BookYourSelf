package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.Location;

public class LocationDTO {

    private Integer id;
    private String airport;
    private String city;
    private String country;
    private Long version;

    public LocationDTO(){}

    public LocationDTO(Integer id, String airport, String city, String country, Long version) {
        this.id = id;
        this.airport = airport;
        this.city = city;
        this.country = country;
        this.version = version;
    }

    public LocationDTO(Location location){
        this(location.getId(), location.getAirport(), location.getCity(), location.getCountry(), location.getVersion());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
