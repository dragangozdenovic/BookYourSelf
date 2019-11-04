package com.example.goBookYourself.model;

import javax.persistence.*;

@Entity
public class ReservationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    @Column(nullable = false, name = "user_username")
    private String username;


    @Column(nullable = false, name = "reservation_id")
    private Integer reservation;

    public ReservationToken(){

    }

    public ReservationToken(String token, Integer reservation, String username) {
        this.token = token;
        this.reservation = reservation;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getReservation() {
        return reservation;
    }

    public void setReservation(Integer reservation) {
        this.reservation = reservation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}