package com.projektowanie.ojektowe.hotel;

import com.projektowanie.ojektowe.hotel.models.Room;
import com.projektowanie.ojektowe.hotel.repositories.RoomRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

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
        int roomClass = 1;
        boolean condition = true;
        boolean WiFi = false;
        boolean pet = true;
        this.room = new Room();
        this.room.setAdult(adult);
        this.room.setChildren(children);
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


}
