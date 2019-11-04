package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //marka
    @Column(name = "producer", nullable = false,length = 50)
    private String producer;

    @Column(name = "model", nullable = false,length = 50)
    private String model;

    @Column(name = "productionYear", nullable = false,length = 50)
    private int productionYear;

    @Column(name = "type", nullable = false , length = 50)
    private String type;

    @Column(name = "pricePerDay", nullable = false,length = 50)
    private int pricePerDay;

    @Column(name="image",columnDefinition = "varchar(50) default 'undefine.jpg'")
    private String image;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rentHouse", nullable = false)
    private RentACar rentHouse;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "branchOffice")
    private BranchOffice branchOffice;

    @Column(name = "posessed_car_report", nullable = false)
    private Boolean possessedCarReport;

    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<CarRating> marks = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPossessedCarReport() {
        return possessedCarReport;
    }

    public void setPossessedCarReport(Boolean possessedCarReport) {
        this.possessedCarReport = possessedCarReport;
    }

    public RentACar getRentHouse() {
        return rentHouse;
    }

    public void setRentHouse(RentACar rentHouse) {
        this.rentHouse = rentHouse;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public Set<CarRating> getMarks() {
        return marks;
    }

    public void setMarks(Set<CarRating> marks) {
        this.marks = marks;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
