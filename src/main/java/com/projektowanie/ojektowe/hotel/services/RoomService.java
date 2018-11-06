package com.projektowanie.ojektowe.hotel.services;

import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Room add(Room room){
        return roomRepository.save(room);
    }

    public List<Room> getAll(){
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }
}
