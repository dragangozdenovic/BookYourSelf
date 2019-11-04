package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.BranchOffice;
import com.example.goBookYourself.model.Car;
import com.example.goBookYourself.model.CarRating;
import com.example.goBookYourself.model.RentACar;

import java.util.HashSet;
import java.util.Set;

public class CarDTO {
    private Integer id;
    private Boolean possessedCarReport;
    private String producer;
    private String model;
    private String image;
    private int productionYear;
    private String type;
    private int pricePerDay;
    private RentACarDTO rentHouse;
    private BranchOfficeDTO branch;
    private Set<CarRatingDTO> marks = new HashSet<>();

    public CarDTO() {

    }

    public CarDTO(Integer id, Boolean possessedCarReport, String producer, String model, int productionYear,
                  String type, int pricePerDay, RentACar rentHouse, Set<CarRating> marks, BranchOffice branch,String image) {
        this.id = id;
        this.possessedCarReport = possessedCarReport;
        this.producer=producer;
        this.model=model;
        this.productionYear=productionYear;
        this.type=type;
        this.pricePerDay=pricePerDay;
        this.rentHouse = new RentACarDTO(rentHouse);
        this.branch=new BranchOfficeDTO(branch);
        for(CarRating mark : marks){
            this.marks.add(new CarRatingDTO(mark));
        }
        this.image=image;
    }

    public CarDTO(Car car) {
        this(car.getId(), car.getPossessedCarReport(), car.getProducer(), car.getModel(), car.getProductionYear(),
                 car.getType(), car.getPricePerDay(), car.getRentHouse(), car.getMarks(),car.getBranchOffice(),car.getImage());
    }

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

    public RentACarDTO getRentHouse() {
        return rentHouse;
    }

    public void setRentHouse(RentACarDTO rentHouse) {
        this.rentHouse = rentHouse;
    }

    public BranchOfficeDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchOfficeDTO branch) {
        this.branch = branch;
    }

    public Set<CarRatingDTO> getMarks() {
        return marks;
    }

    public void setMarks(Set<CarRatingDTO> marks) {
        this.marks = marks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

