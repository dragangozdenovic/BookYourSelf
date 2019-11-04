package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    void removeById(Integer id);
    Room findById(int id);
}
