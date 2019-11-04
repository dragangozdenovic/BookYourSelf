package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.*;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String number;
    private AvioCompanyDTO avioCompany;
    private HotelDTO hotel;
    private List<AuthorityDTO> authority;
    private boolean passChanged;

    public UserDTO() {
    }

    public UserDTO(Integer id, String username, String email, String password,
                   String name, String surname, String city, String number, AvioCompany avioCompany, Hotel hot,List<Authority> au, boolean passChanged) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.number = number;
        this.passChanged = passChanged;

        if (avioCompany != null)
            this.avioCompany = new AvioCompanyDTO(avioCompany);
        if(hot!=null)
            this.hotel = new HotelDTO(hot);
        authority = new ArrayList<>();
        for(Authority auth : au)
        {
            authority.add(new AuthorityDTO(auth));
        }
    }

    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                user.getName(), user.getSurname(), user.getCity(), user.getNumber(), user.getAvioCompany(),user.getAdmin_hotel(),
                user.getAuthorities(), user.isPassChanged());



    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AvioCompanyDTO getAvioCompany() {
        return avioCompany;
    }

    public void setAvioCompany(AvioCompanyDTO avioCompany) {
        this.avioCompany = avioCompany;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    public List<AuthorityDTO> getAuthority() {
        return authority;
    }

    public void setAuthority(List<AuthorityDTO> authority) {
        this.authority = authority;
    }
    public boolean isPassChanged() {
        return passChanged;
    }

    public void setPassChanged(boolean passChanged) {
        this.passChanged = passChanged;
    }
}
