package com.projektowanie.ojektowe.hotel.controllers;

import com.projektowanie.ojektowe.hotel.exceptions.EmptyReservationFilterException;
import com.projektowanie.ojektowe.hotel.exceptions.ReservationEndBeforeStartException;
import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.utils.ReservationFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.services.Factories.ReservationServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ReservationController {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) throws ReservationEndBeforeStartException {
        Reservation addedReservation = ReservationServiceFactory.getReservationService(this.reservationRepository).add(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReservation);
    }

    @GetMapping("/filterSearch")
    public List<Reservation> getByFilter(@RequestBody ReservationFilter reservationFilter) throws EmptyReservationFilterException {
        return ReservationServiceFactory.getReservationService(this.reservationRepository).getById(reservationFilter);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        ReservationServiceFactory.getReservationService(this.reservationRepository).delete(id);
    }
}
