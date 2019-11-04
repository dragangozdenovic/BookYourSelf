package com.example.goBookYourself.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}