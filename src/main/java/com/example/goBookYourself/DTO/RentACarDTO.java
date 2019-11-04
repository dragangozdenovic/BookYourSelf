package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.BranchOffice;
import com.example.goBookYourself.model.RentACar;

import java.util.HashSet;
import java.util.Set;

public class RentACarDTO {
    private Integer id;
    private String name;
    private String image;
    private String address;
    private String promotingAdd;


    public RentACarDTO() {

    }
    public  RentACarDTO(Integer id, String name,String image,String address, String promotingAdd) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.promotingAdd = promotingAdd;
    }

    public RentACarDTO(RentACar rent) {
        this(rent.getId(), rent.getName(), rent.getImage(),rent.getAddress(), rent.getPromotingAdd());
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

    public String getPromotingAdd() {
        return promotingAdd;
    }

    public void setPromotingAdd(String promotingAdd) {
        this.promotingAdd = promotingAdd;
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


}
