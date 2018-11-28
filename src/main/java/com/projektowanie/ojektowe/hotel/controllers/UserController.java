package com.projektowanie.ojektowe.hotel.controllers;

import com.projektowanie.ojektowe.hotel.exceptions.UserAlreadyExistException;
import com.projektowanie.ojektowe.hotel.exceptions.UserDoesentExistException;
import com.projektowanie.ojektowe.hotel.models.UserModels.LoginUser;
import com.projektowanie.ojektowe.hotel.models.UserModels.User;
import com.projektowanie.ojektowe.hotel.repositories.UserRepository;
import com.projektowanie.ojektowe.hotel.services.Factories.UserServiceFactory;
import com.projektowanie.ojektowe.hotel.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserAlreadyExistException {
        User addedUser = UserServiceFactory.getUserService(userRepository).registerUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(addedUser);
    }

    @PostMapping("/email")
    public ResponseEntity<User> login(@RequestBody LoginUser loginUser)throws UserDoesentExistException {
        return ResponseEntity.status(HttpStatus.OK).body(UserServiceFactory.getUserService(userRepository).logIn(loginUser));
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return UserServiceFactory.getUserService(userRepository).getAllUsers();
    }
}
