package com.projektowanie.ojektowe.hotel.repositories;

import com.projektowanie.ojektowe.hotel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
