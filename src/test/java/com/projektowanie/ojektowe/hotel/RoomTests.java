package com.projektowanie.ojektowe.hotel;

import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.utils.RoomFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class RoomTests {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void getFreeGroupsTest() {
        RoomFilter roomFilter = new RoomFilter(
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime(),
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime(),
                1d, 0, 20, false, false, false,
                0d, 10);

        Room room1 = new Room(
                1, 2d, 10, false, true, false, 3d);
        Room room2 = new Room(
                2, 2d, 10, false, true, false, 3d);
        Room room3 = new Room(
                3, 2d, 10, false, true, false, 3d);
        Room room4 = new Room(
                4, 2d, 12, false, true, false, 3d);
        Room room5 = new Room(
                5, 2d, 123, false, true, false, 3d);
        Room room6 = new Room(
                6, 2d, 12, false, true, false, 3d);

        List<Room> roomlist = Arrays.asList(room1, room2, room3, room4, room5, room6);

        when(roomRepository.findAll(Sort.by(Direction.DESC, "roomClass"))).thenReturn(roomlist);
        when(reservationRepository.findAllByRoomNumber(anyInt())).thenReturn(new ArrayList<>());
        List<List<Room>> result = roomService.getFreeGrouped(roomFilter);
        assertEquals(4, result.get(0).size());
    }

    @Test
    public void shouldReturnOnlyOneOnlyOneFreeRoom() {
        RoomFilter roomFilter = new RoomFilter(
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 20).getTime(),
                new GregorianCalendar(2019, Calendar.DECEMBER, 20).getTime(),
                1d, 0, 20, true, true, true,
                0d, 0);

        Room room1 = new Room(
                1, 2d, 10, true, false, false, 3d);
        Room room2 = new Room(
                2, 2d, 10, false, true, false, 3d);
        Room room3 = new Room(
                3, 2d, 10, false, false, true, 3d);
        Room room4 = new Room(
                4, 2d, 12, false, false, false, 3d);
        Room room5 = new Room(
                5, 2d, 3, true, true, true, 3d);
        Room room6 = new Room(
                6, 2d, 12, true, true, true, 3d);

        List<Room> roomlist = Arrays.asList(room1, room2, room3, room4, room5, room6);


        Reservation reservation = new Reservation(0, 5, 2, 1, 100d,
                new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime(),
                new GregorianCalendar(2020, Calendar.AUGUST, 20).getTime());

        when(roomRepository.findAll(Sort.by(Direction.ASC, "roomClass"))).thenReturn(roomlist);
        when(reservationRepository.findAllByRoomNumber(1)).thenReturn(new ArrayList<>());
        when(reservationRepository.findAllByRoomNumber(2)).thenReturn(new ArrayList<>());
        when(reservationRepository.findAllByRoomNumber(3)).thenReturn(new ArrayList<>());
        when(reservationRepository.findAllByRoomNumber(4)).thenReturn(new ArrayList<>());
        when(reservationRepository.findAllByRoomNumber(5)).thenReturn(Arrays.asList(reservation));
        when(reservationRepository.findAllByRoomNumber(6)).thenReturn(new ArrayList<>());
        List<Room> result = roomService.getFree(roomFilter);

        assertEquals(1, result.size());

    }


}
