package com.gestion.exams.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.dto.PeriodDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.PeriodRepository;

@Service
public class PeriodService {

	@Autowired
	private PeriodRepository periodRepository;

	private ModelMapper modelMapper = new ModelMapper();

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

	public boolean verifyIfExamAlreadyExist(UE ue, long id) {
		Period period = getPeriod(id);
		for(Exam exam: period.getExams()) {
			if(exam.getUe().getName().compareTo(ue.getName())==0) {
				return true;
			}
		}
		return false;
	}

	public List<PeriodDTO> getListPeriod(){
		List<PeriodDTO> listPeriodDTO = new ArrayList<>();

		for(Period period : periodRepository.findAll()) {
			listPeriodDTO.add(convertToDTO(period));
		}

		return listPeriodDTO;
	}

	public PeriodDTO convertToDTO(Period period) {
		PeriodDTO periodDTO = modelMapper.map(period, PeriodDTO.class);
		periodDTO.setBeginDatePeriod(period.getBeginDatePeriod());
		periodDTO.setEndDatePeriod(period.getEndDatePeriod());
		return periodDTO;
	}

	public Period convertToEntity(PeriodDTO periodDTO) throws ParseException{
		Period period = modelMapper.map(periodDTO, Period.class);
		period.setBeginDatePeriod(periodDTO.getBeginDatePeriod());
		period.setEndDatePeriod(periodDTO.getEndDatePeriod());
		period.setId(periodDTO.getId());

		return period;
	}

	public Period getPeriod(long id) {
		return periodRepository.findById(id).get();
	}

	public Period getPeriodFromMap(Map<String, String> mapPeriod) {
		try {
			return new Period(DateService.convertStringDateYearFirstToDateClass(mapPeriod.get("beginDatePeriod")),
					DateService.convertStringDateYearFirstToDateClass(mapPeriod.get("endDatePeriod")), mapPeriod.get("name"));
		}catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	public Period savePeriod(Period periodToSave) {
		return periodRepository.save(periodToSave);
	}

}











