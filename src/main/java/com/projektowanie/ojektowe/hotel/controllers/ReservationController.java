package com.projektowanie.ojektowe.hotel.controllers;

import com.projektowanie.ojektowe.hotel.exceptions.EmptyReservationFilterException;
import com.projektowanie.ojektowe.hotel.exceptions.NoReservationFoundException;
import com.projektowanie.ojektowe.hotel.exceptions.RoomAlreadyReservedException;
import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.utils.ReservationFilter;
import com.projektowanie.ojektowe.hotel.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:5050")
public class ReservationController {
    private IReservationService reservationService;

    @Autowired
    public ReservationController(IReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) throws RoomAlreadyReservedException {
        Reservation addedReservation = reservationService.add(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReservation);
    }

    @GetMapping("/filterSearch")
    public List<Reservation> getByFilter(@RequestBody ReservationFilter reservationFilter) throws EmptyReservationFilterException, NoReservationFoundException {
        return reservationService.getByNameOrId(reservationFilter);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        reservationService.delete(id);
    }
}
