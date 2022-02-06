package com.gestion.exams.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public static boolean isBetweenDate(Date beginDate, Date endDate, Date dateToVerify) {
		return !dateToVerify.before(beginDate) && !dateToVerify.after(endDate);
	}

	public static String convertDateClassToStringDate(Date date) {
		return getDay(date) + "/" + getMonth(date) + "/" + getYear(date) + " " + getHour(date)+":00";
	}

	public static Date createDate(String day, String month, String year, String hour) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year+"-"+month+"-"+day+" " + hour+":00:00");
	}

	public static Date createDateWithMinute(String day, String month, String year, String hour, String minute) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year+"-"+month+"-"+day+" " + hour+":" + minute + ":00");
	}

	public static Date convertStringDateToDateClass(String str) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(str);
	}

	public static Date convertStringDateYearFirstToDateClass(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}

	public static Date addHours(Date date, int hour) throws ParseException {
		return createDate(String.valueOf(getDay(date)), String.valueOf(getMonth(date)), String.valueOf(getYear(date)),
				String.valueOf(getHour(date)+(hour/60)));
	}

	public static Date addMinute(Date date, int minute) throws ParseException{
		return createDateWithMinute(String.valueOf(getDay(date)), String.valueOf(getMonth(date)), String.valueOf(getYear(date)),
				String.valueOf(getHour(date)), String.valueOf(minute));
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

	public static int getHour(Date date) {
		Calendar cal = getCalendar(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	private static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	private DateService() {

	}

}
