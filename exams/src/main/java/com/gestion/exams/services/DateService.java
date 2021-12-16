package com.gestion.exams.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public static String convertDateClassToStringDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE) + " - " + cal.get(Calendar.MONTH) + " - " + cal.get(Calendar.YEAR);
	}

	public static Date convertStringDateToDateClass(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}

	private DateService() {

	}

}
