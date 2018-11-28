package com.projektowanie.ojektowe.hotel.services.Factories;

import com.projektowanie.ojektowe.hotel.repositories.UserRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.UserService;
import com.projektowanie.ojektowe.hotel.services.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFactory {

    public static IUserService getUserService(UserRepository userRepository){
        return new UserService(userRepository);
    }
}
