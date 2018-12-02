package com.projektowanie.ojektowe.hotel.services.IMPL;

import com.projektowanie.ojektowe.hotel.exceptions.UserAlreadyExistException;
import com.projektowanie.ojektowe.hotel.exceptions.UserDoesentExistException;
import com.projektowanie.ojektowe.hotel.models.UserModels.LoginUser;
import com.projektowanie.ojektowe.hotel.models.UserModels.User;
import com.projektowanie.ojektowe.hotel.repositories.UserRepository;
import com.projektowanie.ojektowe.hotel.services.IUserService;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.size() == 0) {
            return userRepository.save(user);
        }

        if (allUsers.stream().anyMatch(usr -> usr.getEmail().equals(user.getEmail()))) {
            throw new UserAlreadyExistException(String.format("User with email %s, alredy exist", user.getEmail()));
        }

        return userRepository.save(user);
    }

    @Override
    public User logIn(LoginUser loginUser) throws UserDoesentExistException {
        Optional<User> userData = userRepository.findByEmail(loginUser.getEmail());
        if (!userData.isPresent()) {
            throw new UserDoesentExistException(String.format("User, which try to login, with email %s, doesn't exist", loginUser.getEmail()));
        } else {
            if (!loginUser.getPassword().equals(userData.get().getPassword())) {
                throw new UserDoesentExistException(String.format("User, which try to login, with email %s, pass wrong password", loginUser.getEmail()));
            } else {
                return userData.get();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
