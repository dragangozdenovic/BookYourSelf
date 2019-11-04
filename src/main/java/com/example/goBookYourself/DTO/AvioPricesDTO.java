package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.AvioPrices;

public class AvioPricesDTO {
    private Integer id;
    private String name;
    private float price;
    private Long version;

    public AvioPricesDTO(){}

    public AvioPricesDTO(Integer id, String name, float price, Long version) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.version = version;
    }

    public AvioPricesDTO(AvioPrices avioPrices){
        this(avioPrices.getId(), avioPrices.getName(), avioPrices.getPrice(), avioPrices.getVersion());
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
