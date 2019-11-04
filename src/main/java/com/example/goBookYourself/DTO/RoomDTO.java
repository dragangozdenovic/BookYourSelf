package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.Hotel;
import com.example.goBookYourself.model.Room;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RoomDTO
{
        private int id;
        private String name;
        private String number_of_room;
        //private HashMap<Integer,Double> avg_room; //<number_of_room,ocena>
        private boolean reserved;
        private LocalDate date_in;
        private LocalDate date_out;
        private int hot;
        private int price;
        private int discountPrice;
        private int number_bed;
        private int number_floor;
        private Long version;

        public RoomDTO()
        {

        }

        public RoomDTO(int id,String name,String number_of_room,boolean reserved,LocalDate date_in,LocalDate date_out,int hot,int price,
                       int number_bed,int number_floor,int discount,Long version)
        {
            super();
            this.id = id;
            this.name=name;
            this.number_of_room=number_of_room;
            this.reserved = reserved;
            this.date_in=date_in;
            this.date_out=date_out;
            this.hot=hot;
            this.price=price;
            this.discountPrice=discount;
            this.number_bed=number_bed;
            this.number_floor=number_floor;
            this.version = version;
        }

        public RoomDTO(Room r)
        {
                this(r.getId(),r.getName(),r.getNumber_of_room(),r.isReserved(),r.getDate_in(),r.getDate_out(),r.getHotel().getId(),r.getPrice(),
                        r.getNumber_bed(),r.getNumber_floor(),r.getDiscount_price(),r.getVersion());
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

        public String getNumber_of_room() {
            return number_of_room;
        }

        public void setNumber_of_room(String number_of_room) {
            this.number_of_room = number_of_room;
        }

        //prosecna ocena sobe pojedinacno
        /*public double getAvg_room(Integer number_room) {
            {
                double rate=0; //pocetna ocena
                double ukupno=0;
                for(Map.Entry<Integer,Double> entry : avg_room.entrySet())
                {
                    if(number_room == entry.getKey())
                    {
                        rate += entry.getValue();
                        ukupno++;
                    }
                }

                return  rate/ukupno;
            }
        }*/


        public boolean isReserved() {
            return reserved;
        }

        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }

    public LocalDate getDate_in() {
        return date_in;
    }

    public void setDate_in(LocalDate date_in) {
        this.date_in = date_in;
    }

    public LocalDate getDate_out() {
        return date_out;
    }

    public void setDate_out(LocalDate date_out) {
        this.date_out = date_out;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber_bed() {
        return number_bed;
    }

    public void setNumber_bed(int number_bed) {
        this.number_bed = number_bed;
    }

    public int getNumber_floor() {
        return number_floor;
    }

    public void setNumber_floor(int number_floor) {
        this.number_floor = number_floor;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
