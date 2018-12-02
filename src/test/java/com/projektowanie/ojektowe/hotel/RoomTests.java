package com.projektowanie.ojektowe.hotel;

import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.models.utils.RoomFilter;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.RoomService;
import org.junit.Before;
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
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RoomTests {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    private Room room;

    @Before
    public void before(){
        int adult = 3;
        int children = 0;
        int number = 1;
        Double roomClass = 1d;
        boolean condition = true;
        boolean WiFi = false;
        boolean pet = true;
        this.room = new Room();
        this.room.setNumber(number);
        this.room.setRoomClass(roomClass);
        this.room.setConditioning(condition);
        this.room.setPetFriendly(pet);
        this.room.setWiFi(WiFi);
    }

    @Test
    public void addRoomTest(){
        when(roomRepository.save(this.room)).thenReturn(this.room);
        assertEquals(roomService.add(this.room), this.room);
    }

    @Test
    public void getFreeGroups() {
        RoomFilter roomFilter = new RoomFilter();

        roomFilter.setStart(new GregorianCalendar(2019, Calendar.AUGUST, 20).getTime());
        roomFilter.setEnd(new GregorianCalendar(2019, Calendar.AUGUST, 28).getTime());
        roomFilter.setRating(1d);
        roomFilter.setStartPrice(0);
        roomFilter.setEndPrice(7251123);
        roomFilter.setWiFi(false);
        roomFilter.setConditioning(false);
        roomFilter.setPetFriendly(false);
        roomFilter.setRoomClass(0d);
        roomFilter.setGroup(10);


        Room room1 = new Room(
                234,
                2d,
                1251123,
                false,
                true,
                false,
                3d
        );

        Room room2 = new Room(
                234,
                2d,
                1251123,
                false,
                true,
                false,
                3d
        );

        Room room3 = new Room(
                234,
                2d,
                1251123,
                false,
                true,
                false,
                3d
        );

        Room room4 = new Room(
                234,
                2d,
                1251123,
                false,
                true,
                false,
                3d
        );

        Room room5 = new Room(
                234,
                2d,
                1251123,
                false,
                true,
                false,
                3d
        );

        Room room6 = new Room(
                234,
                2d,
                1251123,
                false,
                true,
                false,
                3d
        );

        List<Room> roomlist = Arrays.asList(
                room1,
                room2,
                room3,
                room4,
                room5,
                room6
        );
        when(roomRepository.findAll(Sort.by(Direction.ASC, "roomClass"))).thenReturn(roomlist);
        List<List<Room>> result = roomService.getFreeGrouped(roomFilter);
        assertEquals(4, result.get(0).size());
    }
}
