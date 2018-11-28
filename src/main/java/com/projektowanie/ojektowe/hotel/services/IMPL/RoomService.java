package com.projektowanie.ojektowe.hotel.services.IMPL;

import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.utils.ROOM_CLASSES;
import com.projektowanie.ojektowe.hotel.models.utils.RoomFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.IRoomService;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomService implements IRoomService {

    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    public RoomService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }
    @Override
    public Room add(Room room) {
        return roomRepository.save(room);
    }
    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }
    @Override
    public void delete(Integer number) {
        roomRepository.deleteById(number);
    }
    @Override
    public List<Room> getFree(RoomFilter roomFilter){
        Map<Room, List<Reservation>> roomsWithReservations;
        List<Room> sortedRoomList = roomRepository.findAll(Sort.by("adult"));

        Date start = roomFilter.getStart();
        Date end = roomFilter.getEnd();

        List<Room> roomsAfterBasicFilters = sortedRoomList.stream()
                .filter(room -> (room.getPrice() >= roomFilter.getStartPrice()))
                .filter(room -> (room.getPrice() <= roomFilter.getEndPrice()))
                .filter(room -> (room.getRating() >= roomFilter.getRating()))
                .filter(room -> ( !roomFilter.getConditioning() || roomFilter.getConditioning().equals(room.getConditioning()))  )
                .filter(room -> ( !roomFilter.getWiFi() || roomFilter.getWiFi().equals(room.getWiFi())) )
                .filter(room -> ( !roomFilter.getPetFriendly() || roomFilter.getPetFriendly().equals(room.getPetFriendly())) )
                .filter(room -> ( roomFilter.getRoomClass().equals(ROOM_CLASSES.ALL.toString().toLowerCase())) || roomFilter.getRoomClass().equals(room.getRoomClass()) )
                //without group reservations
                .collect(Collectors.toList());

        //create map room-reservation list
        roomsWithReservations = roomsAfterBasicFilters.stream().collect(Collectors.toMap(room -> room, room ->
                reservationRepository.findAllByRoomNumber(room.getNumber())));

        return roomsWithReservations.keySet().stream().filter(key ->
                roomsWithReservations.get(key).stream().noneMatch(reserv ->
                        ( ( reserv.getStart().before(start) || reserv.getStart().getTime() == start.getTime() )  && reserv.getEnd().after(start) && reserv.getEnd().before(end) ) ||
                                ( reserv.getStart().after(start) && reserv.getStart().before(end) && ( reserv.getEnd().before(end)|| reserv.getEnd().getTime() == end.getTime() ) ) ||
                                ( reserv.getStart().after(start) && reserv.getStart().before(end) && reserv.getEnd().after(end) ) ||
                                ( reserv.getStart().getTime() == start.getTime() && reserv.getEnd().getTime() == end.getTime() )
                ) ).collect(Collectors.toList());
    }
}
