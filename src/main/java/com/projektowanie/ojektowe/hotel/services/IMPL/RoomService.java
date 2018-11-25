package com.projektowanie.ojektowe.hotel.services.IMPL;

import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.utils.RoomFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    RoomService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public Room add(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public void delete(Integer number) {
        roomRepository.deleteById(number);
    }

//    public List<Room> getFree(Date startDate, Date endDate) {
//        Map<Room, List<Reservation>> roomsWithReservations;
//        List<Room> roomsList = roomRepository.findAll();
//        List<Room> sortedRoomList = roomRepository.findAll(Sort.by("guestPlaces"));
//        //create map room-reservation list
//        roomsWithReservations = roomsList.stream().collect(Collectors.toMap(room -> room, room ->
//                reservationRepository.findAllByRoomNumber(room.getNumber())));
//        //return all rooms which free
//        return roomsWithReservations.keySet().stream().filter(key ->
//                roomsWithReservations.get(key).stream().noneMatch(reserv ->
//                        ( ( reserv.getStart().before(startDate) || reserv.getStart().getTime() == startDate.getTime() )  && reserv.getEnd().after(startDate) && reserv.getEnd().before(endDate) ) ||
//                                ( reserv.getStart().after(startDate) && reserv.getStart().before(endDate) && ( reserv.getEnd().before(endDate)|| reserv.getEnd().getTime() == endDate.getTime() ) ) ||
//                                ( reserv.getStart().after(startDate) && reserv.getStart().before(endDate) && reserv.getEnd().after(endDate) ) ||
//                                ( reserv.getStart().getTime() == startDate.getTime() && reserv.getEnd().getTime() == endDate.getTime() )
//                ) ).collect(Collectors.toList());
//    }


    public List<Room> getFree(RoomFilter roomFilter){
        Map<Room, List<Reservation>> roomsWithReservations;
//        List<Room> roomsList = roomRepository.findAll();
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
                //without group reservations
                .filter(room -> (room.getAdult() >= roomFilter.getAdult()))
                .filter(room -> (room.getChildren() >= roomFilter.getChildren()))
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

//    private List<List<Room>> makeGroups(List<Room> freeRooms, int places){
//        List<List<Room>> groups = new ArrayList<>();
//
//        for( Room freeRoom : freeRooms ){
//
//        }
//
//        return groups;
//    }
}
