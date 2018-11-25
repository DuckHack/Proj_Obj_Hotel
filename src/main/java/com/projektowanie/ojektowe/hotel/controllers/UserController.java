package com.projektowanie.ojektowe.hotel.controllers;

import com.projektowanie.ojektowe.hotel.exceptions.UserAlreadyExistException;
import com.projektowanie.ojektowe.hotel.exceptions.UserDoesentExistException;
import com.projektowanie.ojektowe.hotel.models.UserModels.LoginUser;
import com.projektowanie.ojektowe.hotel.models.UserModels.User;
import com.projektowanie.ojektowe.hotel.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5050")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserAlreadyExistException {
        User addedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(addedUser);
    }

    @PostMapping("/email")
    public ResponseEntity<User> login(@RequestBody LoginUser loginUser)throws UserDoesentExistException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.logIn(loginUser));
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAllUsers();
    }
}
