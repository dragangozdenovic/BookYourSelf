package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AvioCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "address", columnDefinition = "varchar(50) default 'UNKNOWN'")
    private String address;

    @Column(name = "promo_description", columnDefinition = "varchar(500) default 'UNKNOWN'")
    private String promoDescription;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Flight> flights = new HashSet<>();

    @OneToMany(mappedBy = "avioCompany", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<User> admins = new HashSet<>();

    @OneToMany(mappedBy = "avioCompany", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<AvioPrices> prices = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "location_avio_company",
            joinColumns = @JoinColumn(name = "avio_company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
    private Set<Location> locations = new HashSet<>();

    @Column(name="image",length = 50)
    private String image;

    public AvioCompany(){}

    public AvioCompany(String name) {
        this.name = name;
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

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

    public Set<AvioPrices> getPrices() {
        return prices;
    }

    public void setPrices(Set<AvioPrices> prices) {
        this.prices = prices;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
