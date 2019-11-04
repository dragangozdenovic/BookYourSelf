package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.CarRating;

public class CarRatingDTO {
    private Integer id;
    private int mark;

    public CarRatingDTO() {

    }

    public CarRatingDTO(Integer id, int mark) {
        this.id=id;
        this.mark=mark;
    }

    public CarRatingDTO(CarRating carRating) {
        this.id= carRating.getId();
        this.mark= carRating.getMark();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
