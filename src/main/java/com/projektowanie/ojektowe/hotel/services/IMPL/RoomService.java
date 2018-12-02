package com.projektowanie.ojektowe.hotel.services.IMPL;

import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.utils.ROOM_CLASSES;
import com.projektowanie.ojektowe.hotel.models.utils.RoomFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.IRoomService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.*;
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
        return roomRepository.findAll(Sort.by(Direction.ASC,"roomClass"));
    }
    @Override
    public void delete(Integer number) {
        roomRepository.deleteById(number);
    }

    @Override
    public List<List<Room>> getFreeGrouped(RoomFilter roomFilter){
        Map<Room, List<Reservation>> roomsWithReservations;
        List<Room> freeRoomsListInPeriod;
        List<Room> sortedRoomList = roomRepository.findAll(Sort.by(Direction.DESC,"roomClass"));
        Date start = roomFilter.getStart();
        Date end = roomFilter.getEnd();

        List<Room> roomsAfterBasicFilters = getRoomsAfterFiltering(sortedRoomList, roomFilter);
        roomsWithReservations = roomsAfterBasicFilters.stream().collect(Collectors.toMap(room -> room, room ->
                reservationRepository.findAllByRoomNumber(room.getNumber())));

        freeRoomsListInPeriod = getFreeRoomsInPeriod(roomsWithReservations, start, end);

        return getGroupedRooms(freeRoomsListInPeriod, roomFilter.getGroup());

    }

    @Override
    public List<Room> getFree(RoomFilter roomFilter){
        Map<Room, List<Reservation>> roomsWithReservations;
        List<Room> sortedRoomList = roomRepository.findAll(Sort.by(Direction.ASC,"roomClass"));
        Date start = roomFilter.getStart();
        Date end = roomFilter.getEnd();
        List<Room> roomsAfterBasicFilters = getRoomsAfterFiltering(sortedRoomList, roomFilter);
        roomsWithReservations = roomsAfterBasicFilters.stream().collect(Collectors.toMap(room -> room, room ->
                reservationRepository.findAllByRoomNumber(room.getNumber())));
        return getFreeRoomsInPeriod(roomsWithReservations, start, end);
    }


    private List<Room> getRoomsAfterFiltering(List<Room> roomList, RoomFilter roomFilter){
        return roomList.stream()
                .filter(room -> (room.getPrice() >= roomFilter.getStartPrice()))
                .filter(room -> (room.getPrice() <= roomFilter.getEndPrice()))
                .filter(room -> (room.getRating() >= roomFilter.getRating()))
                .filter(room -> ( !roomFilter.getConditioning() || roomFilter.getConditioning().equals(room.getConditioning()))  )
                .filter(room -> ( !roomFilter.getWiFi() || roomFilter.getWiFi().equals(room.getWiFi())) )
                .filter(room -> ( !roomFilter.getPetFriendly() || roomFilter.getPetFriendly().equals(room.getPetFriendly())) )
                .filter(room -> ( roomFilter.getRoomClass() == 0 ) || roomFilter.getRoomClass().equals(room.getRoomClass()) )
                //without group reservations
                .collect(Collectors.toList());

    }

    private List<Room> getFreeRoomsInPeriod(Map<Room, List<Reservation>> roomsWithReservations, Date start, Date end){
        return roomsWithReservations.keySet().stream().filter(key ->
                roomsWithReservations.get(key).stream().noneMatch(reserv ->
                        ( ( reserv.getStart().before(start) || reserv.getStart().getTime() == start.getTime() )  && reserv.getEnd().after(start) && reserv.getEnd().before(end) ) ||
                                ( reserv.getStart().after(start) && reserv.getStart().before(end) && ( reserv.getEnd().before(end)|| reserv.getEnd().getTime() == end.getTime() ) ) ||
                                ( reserv.getStart().after(start) && reserv.getStart().before(end) && reserv.getEnd().after(end) ) ||
                                ( reserv.getStart().getTime() == start.getTime() && reserv.getEnd().getTime() == end.getTime() )
                ) ).collect(Collectors.toList());

    }

    private List<List<Room>> getGroupedRooms(List<Room> freeRooms, Integer groupSize){
        Integer bedsCounter;
        LinkedList<Room> freeRoomsList = new LinkedList<>(freeRooms);
        List<Room> group;
        Room room;
        List<List<Room>> groupedLists = new ArrayList<>();
        while ( freeRoomsList.size() > 0) {
            bedsCounter = 0;
            group = new ArrayList<>();
            while (bedsCounter < groupSize) {
                if (freeRoomsList.size() == 0) {
                    break;
                }
                room = freeRoomsList.poll();
                group.add(room);
                bedsCounter += room.getRoomClass().intValue();
            }
            if( bedsCounter >= groupSize ) {
                groupedLists.add(group);
            }
        }
        return groupedLists;
    }

}
