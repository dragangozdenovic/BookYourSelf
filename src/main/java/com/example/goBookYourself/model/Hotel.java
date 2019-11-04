package com.example.goBookYourself.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name", nullable = false, unique = true , length = 50)
    private String name;

    @Column(name="address", nullable = false, length = 50)
    private String address;

    @Column(name="promoDescription", columnDefinition = "varchar(50) default 'UNKNOWN'")
    private String promoDescription;

    @Column(name="income",length = 10)
    private String income;

    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinTable(
            name = "HotelPriceService",
            joinColumns = { @JoinColumn(name = "hotel_name",referencedColumnName = "name" )},
            inverseJoinColumns = { @JoinColumn(name = "service_name",referencedColumnName = "name")}
    )
    private Set<HotelServicePrice> serviceHotel = new HashSet<>(); //<lista usluga>

    @Column(name="image",length = 200)
    private String image;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Room> room;

    @OneToMany(mappedBy = "admin_hotel", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<User> admins = new HashSet<>();

    //prosecna ocena hotela (unosis samo ocene i na kraju samo odredim prosecnu ocenu)
    @Column(name="rating",length = 200)
    private Double rating; //ocena hotela*/

    //posecenost hotela u odredjenom periodu (dan, nedelja, mesec)
    //@Column(name="visit",length = 200)
    //private List<Integer,Data> visit; <posetio, vreme posete>

    //prihod hotela za odredjeni period
    //@Column(name="priceHotel",lenght=5)
    //private double priceHotel

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Set<HotelServicePrice> getServiceHotel() {
        return serviceHotel;
    }

    public void setServiceHotel(Set<HotelServicePrice> serviceHotel) {
        this.serviceHotel = serviceHotel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Room> getRoom() {
        return room;
    }

    public void setRoom(Set<Room> room) {
        this.room = room;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    /*public void setVisit(Integer i)
    {
        visit.add(i);
    }
    public double getVisit(Integer br_dana)
    {
        double prosek =0;
        for(int i=visit.size()-br_dana-1;i<visit.size();i++)
        {
            prosek+=visit.get(i);
        }

        return prosek/(double)br_dana;
    }*/

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }
}
