package com.projektowanie.ojektowe.hotel.services;

import com.projektowanie.ojektowe.hotel.models.Room;

import java.util.List;

public interface IRoomService {
    Room add(Room room);
    List<Room> getAll();
    void delete(Integer number);
//    List<Room> getFree(Date startDate, Date endDate);
}
