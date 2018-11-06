package com.projektowanie.ojektowe.hotel.models.utils;

import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RoomBuilder {

    private Room room;

    public RoomBuilder buildNewRoom(){
        this.room = new Room();
        this.room.setGuestPlaces(1);
        this.room.setIsBusy(false);
        this.room.setIsReserved(false);
//        this.room.setReservations(new ArrayList<>());
//        this.room.setResidents(new ArrayList<>());
        return this;
    }

    public RoomBuilder setGuestPlaceNumber(Integer places) {
        this.room.setGuestPlaces(places);
        return this;
    }

    public RoomBuilder setBusyStatus(Boolean status){
        this.room.setIsBusy(status);
        return this;
    }

    public RoomBuilder setReserveStatus(Boolean status){
        this.room.setIsReserved(status);
        return this;
    }

//    public RoomBuilder setReservations(List<Reservation> reservations){
//        this.room.setReservations(reservations);
//        return this;
//    }

//    public RoomBuilder setResidents(List<User> residents){
//        this.room.setResidents(residents);
//        return this;
//    }
}
