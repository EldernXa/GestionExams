package com.gestion.exams.jpa;

import com.gestion.exams.entity.Room;
import com.gestion.exams.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Test
    public void findAllTest(){
        Room room1 = new Room("TPR1", 50);
        Room room2 = new Room("TPR2", 60);
        roomRepository.save(room1);
        roomRepository.save(room2);

        List<Room> rooms = roomRepository.findAll();
        assertThat(rooms).isNotEmpty();
        assertThat(rooms).hasSize(2);
    }

    @Test
    public void createRoomTest(){
        Room room1 = new Room("TPR1", 50);
        roomRepository.save(room1);
        Room expected = roomRepository.getById(room1.getName());
        assertThat(expected).isNotNull();
        assertThat(expected.getName()).isEqualTo(room1.getName());
    }
}
