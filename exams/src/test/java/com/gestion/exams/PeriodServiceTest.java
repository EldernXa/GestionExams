package com.gestion.exams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.DateService;
import com.gestion.exams.services.PeriodService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
class PeriodServiceTest {

	@Autowired
	private PeriodService periodService;

	@Autowired
	private PeriodRepository periodRepository;

	@Autowired
	private UERepository ueRepository;

	@Autowired
	private ExamRepository examRepository;

	private Date beginDate;
	private Date endDate;
	private String namePeriod;
	private String nameUe;
	private Period period;
	private Exam exam;

	@BeforeAll
	public void init() throws ParseException {
		beginDate = DateService.convertStringDateToDateClass("03/01/2022");
		endDate = DateService.convertStringDateToDateClass("14/01/2022");
		namePeriod = "period1";
		period = new Period(beginDate, endDate, namePeriod);
		period = periodRepository.save(period);
		nameUe = "Génie Logiciel";
		exam = new Exam(beginDate, endDate,
				1, 2021, null, period, ueRepository.findById(nameUe).get());
		exam = examRepository.save(exam);
	}

	@AfterAll
	public void afterTest() {
		examRepository.delete(exam);
		periodRepository.delete(period);
	}

	@Test
	void testBeginDatePeriodToString() {
		assertEquals(DateService.convertDateClassToStringDate(beginDate), periodService.beginDatePeriodToString(period.getId()));
	}

	@Test
	void testBeginDatePeriodToStringWithFalseId() {
		assertNull(periodService.beginDatePeriodToString(-1));
	}

	@Test
	void testEndDatePeriodToString() {
		assertEquals(DateService.convertDateClassToStringDate(endDate), periodService.endDatePeriodToString(period.getId()));
	}

	@Test
	void testEndDatePeriodToStringWithFalseId() {
		assertNull(periodService.endDatePeriodToString(-1));
	}

	@Test
	void testVerifyingIfExamAlreadyExist() {
		assertTrue(periodService.verifyIfExamAlreadyExist(ueRepository.findById(nameUe).get(), period.getId()));
	}

	@Test
	void testVerifyingThatExamDoesntExist() {
		UE uefinal = null;
		for(UE ue : ueRepository.findAll()) {
			if(ue.getName() != nameUe) {
				uefinal = ue;
				break;
			}
		}

		assertFalse(periodService.verifyIfExamAlreadyExist(uefinal, period.getId()));
	}

}











