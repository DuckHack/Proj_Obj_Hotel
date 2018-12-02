package com.projektowanie.ojektowe.hotel;

import com.projektowanie.ojektowe.hotel.exceptions.ReservationEndBeforeStartException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        new Reservation(0, 1, 2,1, 100d,
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 20).getTime());
        Date date = new Date(2019, 1, 2);
    }

    @Test
    public void shouldAddFirstReservation() throws ReservationEndBeforeStartException {
        Reservation reservation = new Reservation(0, 1, 2,2, 200d,
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 20).getTime());

        //when
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        //assert
        assertEquals(reservationService.add(reservation), reservation);

    }

//    @Test
//    public void reservationShouldBeUpdatedBySeasonDiscount() throws ReservationEndBeforeStartException{
//        int discountSize = 5;
//        Reservation reservation = new Reservation(0, 1, 2,2, 200d,
//                new GregorianCalendar(2019, Calendar.SEPTEMBER, 20).getTime(),
//                new GregorianCalendar(2019, Calendar.NOVEMBER, 20).getTime());
//
//        Reservation updatedReservation = reservationService.add(reservation);
//        when(reservationRepository.findByOwnerId(anyInt())).thenReturn(new ArrayList<>());
//        when(reservationRepository.save(any())).thenReturn()
//        assertEquals(reservation.getPrice() - reservation.getPrice()*discountSize/100, updatedReservation);
//    }
//
}
