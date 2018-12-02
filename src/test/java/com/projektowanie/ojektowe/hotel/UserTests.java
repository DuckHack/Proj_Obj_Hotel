package com.projektowanie.ojektowe.hotel;

import java.util.Optional;
import com.projektowanie.ojektowe.hotel.exceptions.UserAlreadyExistException;
import com.projektowanie.ojektowe.hotel.exceptions.UserDoesentExistException;
import com.projektowanie.ojektowe.hotel.models.UserModels.LoginUser;
import com.projektowanie.ojektowe.hotel.models.UserModels.User;
import com.projektowanie.ojektowe.hotel.repositories.UserRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldRegisterUserAsFirst() throws UserAlreadyExistException {
        User user = new User(1, "User", "Andrei", "Kir", "dup@gmail.com", "12345");
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.registerUser(user));
    }

    @Test(expected = UserAlreadyExistException.class)
    public void shouldNotRegisterUserBecauseOfCollision() throws UserAlreadyExistException {
        when(userRepository.findAll()).thenReturn( Arrays.asList( new User(1, "User", "Andrei", "Kir", "dup@gmail.com", "12345") ));
        User user = new User(1, "User", "Andrei", "Kir", "dup@gmail.com", "12345");
        userService.registerUser(user);
    }

    @Test
    public void shouldRegisterUser() throws UserAlreadyExistException {
        User user = new User(1, "User", "Andrei", "Kir", "vladik@gmail.com", "12345");
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findAll()).thenReturn( Arrays.asList( new User(1, "User", "Andrei", "Kir", "dup@gmail.com", "12345") ));
        assertEquals(user, userService.registerUser(user));
    }

    @Test(expected = UserDoesentExistException.class)
    public void shouldRejectAccess() throws UserDoesentExistException{
        String userEmail = "vladik@gmail.com";
        User user = new User(1, "User", "Andrei", "Kir", userEmail, "12345");
        Optional<User> returnUser = Optional.of((User) user);
        Mockito.<Optional<User>>when(userRepository.findByEmail(userEmail)).thenReturn(returnUser);

        LoginUser loginUser = new LoginUser(userEmail, "123456");
        userService.logIn(loginUser);
    }

    @Test
    public void shouldBeAccepted() throws UserDoesentExistException{
        String userEmail = "vladik@gmail.com";
        User user = new User(1, "User", "Andrei", "Kir", userEmail, "12345");
        Optional<User> returnUser = Optional.of((User) user);
        Mockito.<Optional<User>>when(userRepository.findByEmail(userEmail)).thenReturn(returnUser);

        LoginUser loginUser = new LoginUser(userEmail, "12345");
        User userData = userService.logIn(loginUser);
        assertEquals(user, userData);
    }

}
