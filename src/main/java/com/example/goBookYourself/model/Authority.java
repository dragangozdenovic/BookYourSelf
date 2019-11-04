package com.example.goBookYourself.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 25)
    private String name;

    public Authority(){}

    public Authority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
