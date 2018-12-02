package com.projektowanie.ojektowe.hotel.services.Factories;

import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.RoomService;
import com.projektowanie.ojektowe.hotel.services.IRoomService;

public class RoomServiceFactory {
    public static IRoomService getRoomService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        return new RoomService(roomRepository, reservationRepository);
    }
}
