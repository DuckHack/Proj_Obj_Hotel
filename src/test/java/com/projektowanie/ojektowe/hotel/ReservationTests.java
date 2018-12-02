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

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class ReservationTests {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void reservationShouldBeUpdatedBySeasonDiscount(){
        Double discountSize = 5.0;
        Reservation reservation = new Reservation(0, 1, 2,2, 200d,
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 20).getTime(),
                new GregorianCalendar(2019, Calendar.NOVEMBER, 20).getTime());

        when(reservationRepository.findByOwnerId(anyInt())).thenReturn(new ArrayList<>());
        Double newPrice = reservationService.updateReservationBySeasonDiscount(reservation);
        Double expectedPrice = 200 - 200.0*discountSize/100.0;
        assertEquals(expectedPrice, newPrice, 1);
    }

    @Test
    public void reservationShouldBeUpdatedByConstUserDiscount(){
        Double userReservations = 6.0;
        Reservation reservation = new Reservation(0, 1, 2,2, 200d,
                new GregorianCalendar(2019, Calendar.JUNE, 20).getTime(),
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime());

        when(reservationRepository.findByOwnerId(anyInt())).thenReturn( new ArrayList( Arrays.asList(1,2,3,4,5,6) ));
        Double newPrice = reservationService.updateReservationByConstUserDiscount(reservation);
        Double expectedPrice = 200 - 200.0*userReservations/100.0;
        assertEquals(expectedPrice, newPrice, 1);
    }

}
