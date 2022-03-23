package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Room;
import com.gestion.exams.repository.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
//@RunWith(MockitoJUnitRunner.Silent.class)
public class RoomServiceTest {

    @Autowired
    RoomService roomService;
    @Autowired
    PeriodService periodService;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    UERepository ueRepository;

    private Date beginDate;
    private Date endDate;
    private String namePeriod;
    private String nameUe;
    private Period period;
    private Exam exam;

    @BeforeAll
    public void init() throws ParseException {
        beginDate = DateService.convertStringDateToDateClass("lundi 3 janvier 2022");
        endDate = DateService.convertStringDateToDateClass("vendredi 14 janvier 2022");
        namePeriod = "period1";
        period = new Period(beginDate, endDate, namePeriod);
        period = periodRepository.save(period);
        nameUe = "Génie Logiciel";
        exam = new Exam(beginDate, endDate,
                1, 2021, null, period, ueRepository.findById(nameUe).get());
        exam = examRepository.save(exam);
        System.out.println(exam);
    }

    @Test
    @Transactional
    public void getAvailableRoomTest() throws ParseException {
          Room roomResult = roomService.getAvailableRoom(beginDate, endDate, periodRepository.findAll().get(0).getId());
          assertThat(roomResult).isNotNull();
    }

    @Test
    public void planifiedPeriodRoomTest() throws ParseException {
        periodService.planRoomAndDateOfExams(period.getId());
        Room room = roomService.getAvailableRoom(beginDate, endDate, periodRepository.findAll().get(0).getId());
        assertThat(room).isNotNull();

    }

}
