package com.projektowanie.ojektowe.hotel.controllers;

import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.utils.RoomFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.Factories.RoomServiceFactory;
import com.projektowanie.ojektowe.hotel.services.IMPL.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RoomController {

    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }


    @GetMapping("/getAll")
    public List<Room> getRooms(){
        return RoomServiceFactory.getRoomService(this.roomRepository, this.reservationRepository).getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room){
        Room addedRoom = RoomServiceFactory.getRoomService(this.roomRepository, this.reservationRepository).add(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRoom);
    }

    @DeleteMapping("/delete/{number}")
    public void deleteRoom(@PathVariable(value = "number") String number){
        RoomServiceFactory.getRoomService(this.roomRepository, this.reservationRepository).delete(Integer.parseInt(number));
    }

    @GetMapping("/getFree")
    public List<Room> getFreeRooms(@RequestBody RoomFilter roomFilter){
        return RoomServiceFactory.getRoomService(this.roomRepository, this.reservationRepository).getFree(roomFilter);
    }
}
