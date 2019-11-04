package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class HotelReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique = true,nullable = false)
    private int id;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HotelServicePrice> favour = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<HotelServicePrice> getFavour() {
        return favour;
    }

    public void setFavour(Set<HotelServicePrice> favour) {
        this.favour = favour;
    }
}
