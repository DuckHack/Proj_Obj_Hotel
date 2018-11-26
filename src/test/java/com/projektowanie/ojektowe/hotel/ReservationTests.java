package com.projektowanie.ojektowe.hotel;

import com.projektowanie.ojektowe.hotel.exceptions.RoomAlreadyReservedException;
import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.repositories.UserRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.ReservationService;
import com.projektowanie.ojektowe.hotel.services.IMPL.RoomService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ReservationTests {

    private Reservation reservation;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ReservationService reservationService;

    @Before
    public void before(){
        new Reservation(0, 1, 2, "UlCh",1,
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 20).getTime());
//        new Reservation(0, 1, 2, "UlCh", new Date("20-10-2019"), new Date("20-11-2019"));
//        new Reservation(0, 1, 2, "UlCh", new Date("20-12-2019"), new Date("20-01-2020"));
    }

    @Test
    public void shouldAddFirstReservation() throws RoomAlreadyReservedException {
        Reservation reservation = new Reservation(0, 1, 2, "UlCh",2,
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 20).getTime());

        //when
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        //assert
        assertEquals(reservationService.add(reservation), reservation);

    }

}
