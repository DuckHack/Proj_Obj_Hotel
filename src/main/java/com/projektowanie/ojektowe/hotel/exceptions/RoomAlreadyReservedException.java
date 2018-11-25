package com.projektowanie.ojektowe.hotel.exceptions;

public class RoomAlreadyReservedException extends Exception {
    public RoomAlreadyReservedException(String msg){
        super(msg);
    }
}
