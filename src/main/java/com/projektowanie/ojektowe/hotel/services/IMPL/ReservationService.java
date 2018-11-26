package com.projektowanie.ojektowe.hotel.services.IMPL;

import com.projektowanie.ojektowe.hotel.exceptions.EmptyReservationFilterException;
import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.utils.ReservationFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation add(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getByNameOrId(ReservationFilter reservationFilter)throws EmptyReservationFilterException{
        if( reservationFilter.getOwnerId() != null ){
            return reservationRepository.findByOwnerId(reservationFilter.getOwnerId());
        }else if( reservationFilter.getReservePerson() != null){
            return reservationRepository.findByReservePerson(reservationFilter.getReservePerson());
        }else if( reservationFilter.getRoom() != null){
            return reservationRepository.findAllByRoomNumber(reservationFilter.getRoom());
        }else {
            throw new EmptyReservationFilterException("Empty reservation filter got");
        }
    }

    @Override
    public void delete(@NotBlank String id){
        reservationRepository.deleteById(Integer.parseInt(id));
    }
}
