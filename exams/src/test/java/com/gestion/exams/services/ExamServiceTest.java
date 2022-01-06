package com.gestion.exams.services;

import java.text.ParseException;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Room;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.RoomRepository;
import com.gestion.exams.repository.UERepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
class ExamServiceTest {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private PeriodRepository periodRepository;

	@Autowired
	private UERepository ueRepository;

	@Autowired
	private ExamService examService;

	private Date beginDate;
	private Date endDate;
	private int session;
	private int year;
	private Room room;
	private Period period;
	private UE ue;
	private Exam exam;
	private int sizeExamListBeforeAdding;

	@BeforeAll
	void init() throws ParseException {
		sizeExamListBeforeAdding = examRepository.findAll().size();
		beginDate = DateService.convertStringDateToDateClass("03/01/2022");
		endDate = DateService.convertStringDateToDateClass("14/01/2022");
		session = 1;
		year = 2022;
		room = roomRepository.findAll().get(0);
		period = periodRepository.findAll().get(0);
		ue = ueRepository.findAll().get(0);
		exam = new Exam(beginDate, endDate, session, year, room, period, ue);
		exam = examRepository.save(exam);

	}

	private

	@AfterAll
	void afterTests() {
		examRepository.delete(exam);
	}

	@Test
	void testGettingAllExams() {
		assertEquals(sizeExamListBeforeAdding+1, examService.getAllExams().size());
	}

	@Test
	void testGettingAllExamsFromAPeriod() {
		assertTrue(examService.getAllExamsFromPeriod(period.getId()).size()>=1);
	}

	@Test
	void testGettingAllExamsFromAPeriodWithInvalidId() {
		assertThrows(NoSuchElementException.class, ()->{
			examService.getAllExamsFromPeriod(-1);
		});
	}


}
