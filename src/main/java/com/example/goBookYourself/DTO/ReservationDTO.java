package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.Reservation;
import com.example.goBookYourself.model.Ticket;

import java.util.HashSet;
import java.util.Set;

public class ReservationDTO {

    private Integer id;
    private Set<TicketDTO> tickets = new HashSet<>();

    public ReservationDTO(){}

    public ReservationDTO(Integer id, Set<Ticket> tickets) {
        this.id = id;

        for(Ticket ticket : tickets)
            this.tickets.add(new TicketDTO(ticket));
    }

    public ReservationDTO(Reservation reservation){
        this(reservation.getId(), reservation.getTickets());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
