package com.projektowanie.ojektowe.hotel.exceptions;


public class ReservationEndBeforeStartException extends Exception {
    public ReservationEndBeforeStartException(String msg){
        super(msg);
    }
}
