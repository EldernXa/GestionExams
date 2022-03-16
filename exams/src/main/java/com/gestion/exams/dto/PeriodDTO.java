package com.gestion.exams.dto;

import java.text.ParseException;
import java.util.Date;

import com.gestion.exams.services.DateService;

public class PeriodDTO {

	private long id;

	private String name;

	private String beginDatePeriod;

	private String endDatePeriod;

	public Date getBeginDatePeriodInDateFormat() throws ParseException {
		return DateService.convertStringDateToDateClass(beginDatePeriod);
	}

	public String getBeginDatePeriod() {
		return this.beginDatePeriod;
	}

	public void setBeginDatePeriod(Date date) {
		this.beginDatePeriod = DateService.convertDateClassToStringDate(date);
	}

	public Date getEndDatePeriodInDateFormat() throws ParseException {
		return DateService.convertStringDateToDateClass(endDatePeriod);
	}

	public String getEndDatePeriod() {
		return this.endDatePeriod;
	}

	public void setEndDatePeriod(Date date) {
		this.endDatePeriod = DateService.convertDateClassToStringDate(date);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
