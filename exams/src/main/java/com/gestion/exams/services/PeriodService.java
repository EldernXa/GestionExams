package com.gestion.exams.services;

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
		try {
			Period period = periodRepository.getById(id);
			return DateService.convertDateClassToStringDate(period.getBeginDatePeriod());
		}catch(Exception exception) {
			return null;
		}
	}

	public String endDatePeriodToString(long id) {
		try {
			Period period = periodRepository.getById(id);
			return DateService.convertDateClassToStringDate(period.getEndDatePeriod());
		}catch(Exception exception) {
			return null;
		}
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











