package com.gestion.exams.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class DateServiceTest {

	private Date date;
	private String dateIntoStringWithYearInLast;
	private String dateIntoStringWithYearFirst;
	private Calendar calendar;

	@BeforeAll
	void init() throws ParseException {
		date = new SimpleDateFormat("dd/MM/yyyy").parse("25/01/2022");
		dateIntoStringWithYearInLast = "1/1/2022";
		dateIntoStringWithYearFirst = "2022-4-2";
		calendar = Calendar.getInstance();
	}

	@Test
	void testConvertDateClassToStringDate() {
		calendar.setTime(date);
		String[] newDateIntoString = DateService.convertDateClassToStringDate(date).split("/");
		assertEquals(DateService.getDay(date), Integer.valueOf(newDateIntoString[0]));
		assertEquals(DateService.getMonth(date), Integer.valueOf(newDateIntoString[1]));
		assertEquals(DateService.getYear(date), Integer.valueOf(newDateIntoString[2]));
	}

	@Test
	void testConvertStringDateToDateClass() throws ParseException {
		Date newDateAfterConverting = DateService.convertStringDateToDateClass(dateIntoStringWithYearInLast);
		String[] dateSplitted = dateIntoStringWithYearInLast.split("/");
		calendar.setTime(newDateAfterConverting);
		assertEquals(Integer.valueOf(dateSplitted[0]), DateService.getDay(newDateAfterConverting));
		assertEquals(Integer.valueOf(dateSplitted[1]), DateService.getMonth(newDateAfterConverting));
		assertEquals(Integer.valueOf(dateSplitted[2]), DateService.getYear(newDateAfterConverting));
	}

	@Test
	void testConvertStringDateYearFirstToDateClass() throws ParseException {
		Date newDateAfterConverting = DateService.convertStringDateYearFirstToDateClass(dateIntoStringWithYearFirst);
		String[] dateSplitted = dateIntoStringWithYearFirst.split("-");
		calendar.setTime(newDateAfterConverting);
		assertEquals(Integer.valueOf(dateSplitted[0]), DateService.getYear(newDateAfterConverting));
		assertEquals(Integer.valueOf(dateSplitted[1]), DateService.getMonth(newDateAfterConverting));
		assertEquals(Integer.valueOf(dateSplitted[2]), DateService.getDay(newDateAfterConverting));
	}


}










