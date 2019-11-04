package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BranchOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name",nullable = false,length = 50)
    private String name; //naziv

    @Column(name = "address", nullable = false,length = 50)
    private String address; // grad

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name="rentService")
    private RentACar rentService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RentACar getRentService() {
        return rentService;
    }

    public void setRentService(RentACar rentService) {
        this.rentService = rentService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

