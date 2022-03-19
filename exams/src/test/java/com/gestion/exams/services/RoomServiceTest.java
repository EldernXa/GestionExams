package com.gestion.exams.services;

import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Room;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.RoomRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(MockitoJUnitRunner.Silent.class)
public class RoomServiceTest {

    @InjectMocks
    RoomService roomService;

    Room room = new Room();
    Period period = new Period();

    @org.junit.Test
    @Transactional
    public void getAvailableRoomTest() throws ParseException {
        Date beginDate = new Date(01-27-2022);
        Date endDate = new Date(01-30-2022);

        room = roomService.getAvailableRoom(beginDate, endDate, period.getId());

        assertEquals(null, room);
    }
}
