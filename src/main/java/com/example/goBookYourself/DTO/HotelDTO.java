package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.Hotel;
import com.example.goBookYourself.model.HotelServicePrice;
import com.example.goBookYourself.model.Room;

import java.util.HashSet;
import java.util.Set;

public class HotelDTO
{
    private int id;
    private String name;
    private String address;
    private String promoDescription;
    private String income;
    private String image;
    private Set<RoomDTO> rooms;
    private Set<HotelServicePriceDTO> priceList;
    //private HashMap<String, Integer> rating; //<ime hotela, ocena>

    public HotelDTO()
    {

    }
    public HotelDTO(int id,String name,String address,String promoDescription,String income,String image,Set<Room>rooms,
                    Set<HotelServicePrice> price)
    {
            super();
            this.id=id;
            this.name=name;
            this.address=address;
            this.promoDescription=promoDescription;
            this.income=income;
            this.image=image;
            this.rooms = new HashSet<>();
            this.priceList = new HashSet<>();
            for(Room r : rooms)
            {
                this.rooms.add(new RoomDTO(r));
            }
            for(HotelServicePrice ht : price)
            {
                this.priceList.add(new HotelServicePriceDTO(ht));
            }

    }

    public HotelDTO(Hotel h)
    {
        this(h.getId(),h.getName(),h.getAddress(),h.getPromoDescription(),h.getIncome(),h.getImage(),h.getRoom(),
                h.getServiceHotel());
    }

    public int getId() {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPromoDescription()
    {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription)
    {
        this.promoDescription = promoDescription;
    }

   /* public HashMap<String, Double> getPriceList()
    {
        return priceList;
    }*/

    /*public void setPriceList(HashMap<String, Double> priceList)
    {
        this.priceList = priceList;
    }*/

    /*public HashMap<String, Integer> getRating()
    {
        return rating;
    }*/

    /*public void setRating(HashMap<String, Integer> rating)
    {
        this.rating = rating;
    }*/

    public String getIncome()
    {
        return income;
    }

    public void setIncome(String income)
    {
        this.income = income;
    }

   /* public double getAverageRatingHotel() {
        double cumulativeRating = 0;

        for (Map.Entry<String, Integer> entry : rating.entrySet()) {
            cumulativeRating += entry.getValue();
        }

        return cumulativeRating / rating.size();
    }*/

    /*public void setVisit(Integer i)
    {
        visit.add(i);
    }
    public double getVisit(Integer br_dana)
    {
        double prosek =0;
        for(int i=visit.size()-br_dana-1;i<visit.size();i++)
        {
            prosek+=visit.get(i);
        }

        return prosek/(double)br_dana;
    }*/

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public Set<HotelServicePriceDTO> getPriceList() {
        return priceList;
    }

    public void setPriceList(Set<HotelServicePriceDTO> priceList) {
        this.priceList = priceList;
    }
}
