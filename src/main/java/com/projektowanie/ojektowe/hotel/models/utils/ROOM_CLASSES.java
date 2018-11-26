package com.projektowanie.ojektowe.hotel.models.utils;

public enum ROOM_CLASSES {
    TWIN(2), DOUBLE(2), TRIPLE(3), QUAD(4), ALL(0);
    private Integer persons;
    ROOM_CLASSES(Integer persons){
        this.persons = persons;
    }

    public Integer getPersons(){
        return this.persons;
    }
}
