package com.projektowanie.ojektowe.hotel.repositories;

import com.projektowanie.ojektowe.hotel.models.UserModels.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String name);
}
