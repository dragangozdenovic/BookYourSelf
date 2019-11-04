package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Room;
import com.example.goBookYourself.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService
{
    @Autowired
    private RoomRepository roomrepository;

    public Room findById(int id)
    {
        return roomrepository.findById(id);
    }

    public List<Room> findAll()
    {
        return roomrepository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void removeById(int id){ roomrepository.removeById(id);}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Room save(Room r)
    {
        return roomrepository.save(r);
    }


}
