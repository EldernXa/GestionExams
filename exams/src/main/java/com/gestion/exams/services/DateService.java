package com.gestion.exams.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	private static final String HOUR_END_DAY = "18";

	private static final String FORMAT_DISPLAY_DATE = "EEEEE dd MMMMM yyyy"; // two formats available : EEEEE dd MMMMM yyyy and dd/MM/yyyy HH:mm

	public static boolean isBetweenDate(Date beginDate, Date endDate, Date dateToVerify) {
		return !dateToVerify.before(beginDate) && !dateToVerify.after(endDate);
	}

	public static Date getNoonOfADate(Date date) throws ParseException{
		return createDateWithMinute(String.valueOf(getDay(date)), String.valueOf(getMonth(date)), String.valueOf(getYear(date)), "12", "01");
	}

	public static Date getAfterNoonOfADate(Date date) throws ParseException{
		return createDate(String.valueOf(getDay(date)), String.valueOf(getMonth(date)), String.valueOf(getYear(date)), "14");
	}

	public static Date getAfterDayOfADate(Date date) throws ParseException{
		return createDate(String.valueOf(getDay(date)), String.valueOf(getMonth(date)), String.valueOf(getYear(date)), HOUR_END_DAY);
	}

	public static Date getDateMinusOneMinute(Date date) throws ParseException {
		return createDateWithMinute(String.valueOf(getDay(date)), String.valueOf(getMonth(date)), String.valueOf(getYear(date)),
				String.valueOf(getHour(date)-1), "59");
	}

	public static Date getTheDayAfterAt8Hour(Date date) throws ParseException{
		return createDate(String.valueOf(getDay(date)+1), String.valueOf(getMonth(date)), String.valueOf(getYear(date)), "08");
	}

	public static Date getTheDayAfterWeekEnd(Date date) throws ParseException{
		if(getDayInString(date).contentEquals("samedi")) {
			return createDate(String.valueOf(getDay(date)+2), String.valueOf(getMonth(date)), String.valueOf(getYear(date)), "08");
		}
		return createDate(String.valueOf(getDay(date)+1), String.valueOf(getMonth(date)), String.valueOf(getYear(date)), "08");
	}

	public static String convertDateClassToStringDate(Date date) {
		return new SimpleDateFormat(FORMAT_DISPLAY_DATE).format(date);
	}

	public static Date convertStringDateToDateClass(String str) throws ParseException {
		return new SimpleDateFormat(FORMAT_DISPLAY_DATE).parse(str);
	}

	public static String convertDateClassToStringDateOnlyForHour(Date date){
		return new SimpleDateFormat("HH:mm").format(date);
	}

	public static String convertDateClassToFullStringDate(Date date) {
		return new SimpleDateFormat(FORMAT_DISPLAY_DATE + " HH:mm").format(date);
	}

	public static Date createDate(String day, String month, String year, String hour) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year+"-"+month+"-"+day+" " + hour+":00:00");
	}

	public static Date createDateWithMinute(String day, String month, String year, String hour, String minute) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year+"-"+month+"-"+day+" " + hour+":" + minute + ":00");
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

	public static String getDayInString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
		return dateFormat.format(c.getTime());
	}

	private static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	private DateService() {

	}

}
