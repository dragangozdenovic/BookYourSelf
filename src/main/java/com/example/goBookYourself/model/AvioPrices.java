package com.example.goBookYourself.model;

import javax.persistence.*;

@Entity
@Table
public class AvioPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "avio_company")
    private AvioCompany avioCompany;

    @Version
    private Long version;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    public AvioPrices(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AvioCompany getAvioCompany() {
        return avioCompany;
    }

    public void setAvioCompany(AvioCompany avioCompany) {
        this.avioCompany = avioCompany;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
}
