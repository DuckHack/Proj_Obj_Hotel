package com.projektowanie.ojektowe.hotel.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
