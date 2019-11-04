package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findById(Integer id);
    void removeById(Integer id);
}
