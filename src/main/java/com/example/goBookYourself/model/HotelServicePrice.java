package com.example.goBookYourself.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service")
public class HotelServicePrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    //naziv usluge
    @Column(name="name", nullable = false, unique = true , length = 50)
    private String name;

    //cena usluge
    @Column(name="price", nullable = false, length = 50)
    private Double price;

    @Column(name="discount",length = 3,columnDefinition = "varchar(3) default '0'")
    private int discount;

    @Version
    private Long version;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private HotelServicePrice hsp;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HotelServicePrice getHsp() {
        return hsp;
    }

    public void setHsp(HotelServicePrice hsp) {
        this.hsp = hsp;
    }
}
