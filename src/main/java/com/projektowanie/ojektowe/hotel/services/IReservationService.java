package com.projektowanie.ojektowe.hotel.services;

import com.projektowanie.ojektowe.hotel.exceptions.EmptyReservationFilterException;
import com.projektowanie.ojektowe.hotel.exceptions.RoomAlreadyReservedException;
import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.utils.ReservationFilter;

import java.util.List;

public interface IReservationService {
    Reservation add(Reservation reservation) throws RoomAlreadyReservedException;
    List<Reservation> getByNameOrId(ReservationFilter reservationFilter)throws EmptyReservationFilterException;
    void delete(String id);
}
