package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.AvioPrices;
import com.example.goBookYourself.model.Location;

import java.util.HashSet;
import java.util.Set;

public class AvioCompanyDTO {

    private Integer id;
    private String name;
    private String address;
    private String promoDescription;
    private Set<LocationDTO> locations = new HashSet<>();
    private String image;
    private Set<AvioPricesDTO> prices = new HashSet<>();

    public AvioCompanyDTO(){

    }

    public AvioCompanyDTO(Integer id, String name, String address, String promoDescription, Set<Location> locations , String image, Set<AvioPrices> prices) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.promoDescription = promoDescription;
        for(Location location : locations)
            this.locations.add(new LocationDTO(location));
        this.image = image;

        for(AvioPrices price : prices)
            this.prices.add(new AvioPricesDTO(price));

    }

    public AvioCompanyDTO(AvioCompany ac) {
        this(ac.getId(), ac.getName(), ac.getAddress(), ac.getPromoDescription(), ac.getLocations(), ac.getImage(), ac.getPrices());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public Set<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(Set<LocationDTO> locations) {
        this.locations = locations;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<AvioPricesDTO> getPrices() {
        return prices;
    }

    public void setPrices(Set<AvioPricesDTO> prices) {
        this.prices = prices;
    }
}
