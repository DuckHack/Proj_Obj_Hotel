package com.projektowanie.ojektowe.hotel.services;

import com.projektowanie.ojektowe.hotel.exceptions.UserAlreadyExistException;
import com.projektowanie.ojektowe.hotel.exceptions.UserDoesentExistException;
import com.projektowanie.ojektowe.hotel.models.UserModels.LoginUser;
import com.projektowanie.ojektowe.hotel.models.UserModels.User;

import java.util.List;

public interface IUserService {

    User registerUser(User user) throws UserAlreadyExistException;
    User logIn(LoginUser loginUser) throws UserDoesentExistException;
    List<User> getAllUsers();
}
