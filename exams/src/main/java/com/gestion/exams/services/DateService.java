package com.gestion.exams.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public static Date convertStringDateToDateClass(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}

	private DateService() {

	}

}
