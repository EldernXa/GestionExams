package com.gestion.exams.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public static String convertDateClassToStringDate(Date date) {
		return getDay(date) + "/" + getMonth(date) + "/" + getYear(date);
	}

	public static Date convertStringDateToDateClass(String str) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(str);
	}

	public static Date convertStringDateYearFirstToDateClass(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}

	public static int getDay(Date date) {
		Calendar cal = getCalendar(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {
		Calendar cal = getCalendar(date);
		return cal.get(Calendar.MONTH)+1;
	}

	public static int getYear(Date date) {
		Calendar cal = getCalendar(date);
		return cal.get(Calendar.YEAR);
	}

	private static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	private DateService() {

	}

}
