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
//    private RoomService roomService;
//    private UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
//        this.roomService = roomService;
//        this.userRepository = userRepository;
    }

    @Override
    public Reservation add(Reservation reservation){
        return reservationRepository.save(reservation);
    }

//    @Override
//    public Reservation add(Reservation reservation) throws RoomAlreadyReservedException {
//        List<Reservation> reservations = reservationRepository.findAll();
//        if( reservations.size() == 0 ){
//            return reservationRepository.save(reservation);
//        }
//
//        if( reservations.stream().noneMatch(reser -> reser.getRoom().equals(reservation.getRoom())) ){
//            return reservationRepository.save(reservation);
//        }
//
//        if( !isPossibleToReserve(reservation) ){
//            throw new RoomAlreadyReservedException(String.format("Room with number %s in period %s - %s, has reserved",
//                    reservation.getRoom(), reservation.getStart().toString(), reservation.getEnd().toString()));
//        }else{
//            return reservationRepository.save(reservation);
//        }
//    }

    @Override
    public List<Reservation> getByNameOrId(ReservationFilter reservationFilter)throws EmptyReservationFilterException{
        List<Reservation> reservations;
        if( reservationFilter.getId() != null ){
            Optional<Reservation> reservation = reservationRepository.findById(reservationFilter.getId());
            reservations = reservation.map(Arrays::asList).orElseGet(ArrayList::new);
        }else if( reservationFilter.getReservePerson() != null){
            return Arrays.asList( reservationRepository.findByReservePerson(reservationFilter.getReservePerson()) );
        }else if( reservationFilter.getRoom() != null){
            return reservationRepository.findAllByRoomNumber(reservationFilter.getRoom());
        }else {
            throw new EmptyReservationFilterException("Empty reservation filter got");
        }
        return reservations;
    }

    @Override
    public void delete(@NotBlank String id){
        reservationRepository.deleteById(Integer.parseInt(id));
    }

//    private Boolean isPossibleToReserve(Reservation reservation){
//        List<Room> freeRoomsList = roomService.getFree(reservation.getStart(), reservation.getEnd());
//        if( freeRoomsList.size() == 0 ){
//            return false;
//        }
//        return freeRoomsList.stream().anyMatch(room -> room.getNumber().equals(reservation.getRoom()));
//    }
}
