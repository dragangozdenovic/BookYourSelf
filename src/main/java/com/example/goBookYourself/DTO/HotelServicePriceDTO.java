package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.HotelServicePrice;

public class HotelServicePriceDTO {

    private int id;
    private String name;
    private Double price;
    private int discount;
    private Long version;

    public HotelServicePriceDTO()
    {

    }

    public HotelServicePriceDTO (int id,String name ,Double price,int discount,Long version)
    {
        super();
        this.id=id;
        this.name=name;
        this.price=price;
        this.discount=discount;
        this.version=version;
    }
    public HotelServicePriceDTO(HotelServicePrice hsp)
    {
        this(hsp.getId(),hsp.getName(),hsp.getPrice(),hsp.getDiscount(),hsp.getVersion());

    }

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
}
