package com.gestion.exams.services;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.entity.Period;
import com.gestion.exams.repository.PeriodRepository;

@Service
public class PeriodService {

	@Autowired
	private PeriodRepository periodRepository;

	public String beginDatePeriodToString(long id) {
		Period period = periodRepository.getById(id);
		Calendar cal = Calendar.getInstance();
		cal.setTime(period.getBeginDatePeriod());
		return cal.get(Calendar.DATE) + " - " + cal.get(Calendar.MONTH) + " - " + cal.get(Calendar.YEAR);
	}

	public String endDatePeriodToString(long id) {
		Period period = periodRepository.getById(id);
		Calendar cal = Calendar.getInstance();
		cal.setTime(period.getEndDatePeriod());
		return cal.get(Calendar.DATE) + " - " + cal.get(Calendar.MONTH) + " - " + cal.get(Calendar.YEAR);
	}

	public List<Period> getListPeriod(){
		return periodRepository.findAll();
	}

	public Period getPeriod(long id) {
		return periodRepository.getById(id);
	}

	public Period getPeriodFromMap(Map<String, String> mapPeriod) {
		try {
			return new Period(DateService.convertStringDateToDateClass(mapPeriod.get("beginDatePeriod")),
					DateService.convertStringDateToDateClass(mapPeriod.get("endDatePeriod")), mapPeriod.get("name"));
		}catch(Exception exception)
		{
			return null;
		}
	}

	public Period savePeriod(Period periodToSave) {
		return periodRepository.save(periodToSave);
	}

}











