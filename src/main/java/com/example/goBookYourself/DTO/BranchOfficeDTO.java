package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.BranchOffice;
import com.example.goBookYourself.model.RentACar;

public class BranchOfficeDTO {
    private int id;
    private String name;
    private String address;
    private RentACarDTO rentService;

    public BranchOfficeDTO(int id,String name, String address,RentACar rentService) {
        this.id=id;
        this.name=name;
        this.address=address;
        this.rentService = new RentACarDTO(rentService);
    }
    public BranchOfficeDTO(BranchOffice branch) {
        this(branch.getId(),branch.getName(),branch.getAddress(),branch.getRentService());
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RentACarDTO getRentService() {
        return rentService;
    }

    public void setRentService(RentACarDTO rentService) {
        this.rentService = rentService;
    }
}
