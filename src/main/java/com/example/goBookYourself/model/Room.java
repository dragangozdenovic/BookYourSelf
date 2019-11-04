package com.example.goBookYourself.model;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="number_of_room",unique = true,length = 3)
    private String number_of_room;
    //@Column(name="avg_room")
    //private HashMap<Integer,Double> avg_room; //<number_of_room,ocena>
    @Column(name="reserved", columnDefinition = "boolean default false")
    private boolean reserved;

    @Version
    private Long version;

    @Column(name="date_in",nullable = true,length = 15)
    private LocalDate date_in;

    @Column(name="date_out",nullable = true,length = 15)
    private LocalDate date_out;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="hotel_id",nullable = false)
    private Hotel hotel;

    @Column(name="price",nullable = false,length = 3)
    private int price;

    @Column(name="discount_price",columnDefinition = "varchar(3) default '0'")
    private int discount_price;

    @Column(name="number_bed",nullable = false,length = 3)
    private int number_bed;

    @Column(name="number_floor",nullable = false,length = 3)
    private int number_floor;

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

    public String getNumber_of_room() {
        return number_of_room;
    }

    public void setNumber_of_room(String number_of_room) {
        this.number_of_room = number_of_room;
    }

    //prosecna ocena sobe pojedinacno
    /*public double getAvg_room(Integer number_room) {
        {
            double rate=0; //pocetna ocena
            double ukupno=0;
            for(Map.Entry<Integer,Double> entry : avg_room.entrySet())
            {
                if(number_room == entry.getKey())
                {
                    rate += entry.getValue();
                    ukupno++;
                }
            }

            return  rate/ukupno;
        }
    }*/

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public LocalDate getDate_in() {
        return date_in;
    }

    public void setDate_in(LocalDate date_in) {
        this.date_in = date_in;
    }

    public LocalDate getDate_out() {
        return date_out;
    }

    public void setDate_out(LocalDate date_out) {
        this.date_out = date_out;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber_bed() {
        return number_bed;
    }

    public void setNumber_bed(int number_bed) {
        this.number_bed = number_bed;
    }

    public int getNumber_floor() {
        return number_floor;
    }

    public void setNumber_floor(int number_floor) {
        this.number_floor = number_floor;
    }

    public int getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
