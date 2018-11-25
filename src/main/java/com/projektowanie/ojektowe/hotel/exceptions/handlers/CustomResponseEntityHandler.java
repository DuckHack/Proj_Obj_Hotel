package com.projektowanie.ojektowe.hotel.exceptions.handlers;

import com.projektowanie.ojektowe.hotel.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CustomResponseEntityHandler {

    @ExceptionHandler(EmptyReservationFilterException.class)
    public final ResponseEntity EmptyReservationFilterExceptionHandler(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoReservationFoundException.class)
    public final ResponseEntity NoReservationFoundExceptionHandler(){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity UserAlreadyExistExceptionHandler(){
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserDoesentExistException.class)
    public final ResponseEntity UserDoesentExistExceptionHandler(){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnableToAddReservationException.class)
    public final ResponseEntity UnableToAddReservationExceptionHandler(){
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomAlreadyReservedException.class)
    public final ResponseEntity RoomAlreadyReservedExceptionHandler(){
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
}
