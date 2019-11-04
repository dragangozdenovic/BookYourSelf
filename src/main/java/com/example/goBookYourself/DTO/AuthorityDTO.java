package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.Authority;

public class AuthorityDTO {

    private int id;
    private String name;

    public AuthorityDTO()
    {

    }
    public AuthorityDTO(String name)
    {
        this.name=name;
    }
    public AuthorityDTO(Authority auth)
    {
        this(auth.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
