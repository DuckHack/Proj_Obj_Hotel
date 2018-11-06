package com.projektowanie.ojektowe.hotel.controllers;

import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.utils.RoomBuilder;
import com.projektowanie.ojektowe.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/getAll")
    public List<Room> getRooms(){
        return roomService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room){
        Room addedRoom = roomService.add(room);
        RoomBuilder roomBuilder = new RoomBuilder();
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRoom);
    }
}
