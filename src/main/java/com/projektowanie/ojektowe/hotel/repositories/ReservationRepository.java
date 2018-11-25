package com.projektowanie.ojektowe.hotel.repositories;


import com.projektowanie.ojektowe.hotel.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query(value = "select * from reservation where room = :room", nativeQuery = true)
    List<Reservation> findAllByRoomNumber(@Param("room")Integer room);

    @Query(value = "select * from reservation where owner = :owner", nativeQuery = true)
    Reservation findByReservePerson(@Param("owner")String person);

}
