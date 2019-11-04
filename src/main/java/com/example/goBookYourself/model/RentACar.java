package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RentACar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name",nullable = false,length = 50)
    private String name;

    @Column(name="image",nullable = false,length = 100)
    private String image;

    @Column(name = "promotingAdd", nullable = false,columnDefinition = "varchar(200) default 'UNKNOWN'")
    private String promotingAdd;

    @Column(name = "address", columnDefinition = "varchar(50) default 'UNKNOWN'")
    private  String address; //street

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

    public String getPromotingAdd() {
        return promotingAdd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPromotingAdd(String promotingAdd) {
        this.promotingAdd = promotingAdd;
    }
}
