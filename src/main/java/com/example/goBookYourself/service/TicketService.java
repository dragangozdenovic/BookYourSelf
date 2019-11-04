package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Ticket;
import com.example.goBookYourself.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Ticket save(Ticket ticket) {
        Ticket createdTicket = repository.save(ticket);
        return createdTicket;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void removeById(Integer id){
        repository.removeById(id);
    }
}
