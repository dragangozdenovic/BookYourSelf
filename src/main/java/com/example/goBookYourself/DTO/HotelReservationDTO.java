package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.HotelReservation;
import com.example.goBookYourself.model.HotelServicePrice;
import com.example.goBookYourself.model.Room;

import java.util.HashSet;
import java.util.Set;

public class HotelReservationDTO {

    private int id;
    private Set<RoomDTO> rooms;
    private Set<HotelServicePriceDTO> hotelServicePrice;

    public HotelReservationDTO()
    {

    }

    public HotelReservationDTO(int id,Set<Room> rooms, Set<HotelServicePrice> hotelServicePrice)
    {
        super();
        this.id=id;
        this.rooms = new HashSet<>();
        for(Room r : rooms)
        {
            this.rooms.add(new RoomDTO(r));
        }
        this.hotelServicePrice = new HashSet<>();
        for(HotelServicePrice hsp : hotelServicePrice)
        {
            this.hotelServicePrice.add(new HotelServicePriceDTO(hsp));
        }
    }

    public HotelReservationDTO(HotelReservation hr)
    {
        this(hr.getId(),hr.getRooms(),hr.getFavour());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public Set<HotelServicePriceDTO> getHotelServicePrice() {
        return hotelServicePrice;
    }

    public void setHotelServicePrice(Set<HotelServicePriceDTO> hotelServicePrice) {
        this.hotelServicePrice = hotelServicePrice;
    }
}
