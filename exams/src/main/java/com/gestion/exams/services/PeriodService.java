package com.gestion.exams.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.dto.PeriodDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Room;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.RoomRepository;

@Service
public class PeriodService {

	@Autowired
	private PeriodRepository periodRepository;

	@Autowired
	private ExamService examService;

	@Autowired
	private RoomRepository roomRepository; // TODO to remove

	@Autowired
	private RoomService roomService;

	private ModelMapper modelMapper = new ModelMapper();

	public PeriodDTO planRoomAndDateOfExams(long id) throws ParseException {
		Period periodToPlan = periodRepository.getById(id);
		List<Exam> listExamFromAPeriod = periodToPlan.getExams();
		Date dateBegin = periodToPlan.getBeginDatePeriod();
		for(Exam exam : listExamFromAPeriod) {
			Date dateBeginWithHour = DateService.createDate(String.valueOf(DateService.getDay(dateBegin)),
					String.valueOf(DateService.getMonth(dateBegin)), String.valueOf(DateService.getYear(dateBegin)), "08");
			exam.setBeginDateExam(dateBeginWithHour);
			exam.setEndDateExam(DateService.addHours(dateBeginWithHour, exam.getUe().getDurationExam()));
			setRoom(exam, periodToPlan);
		}

		return convertToDTO(periodToPlan);
	}

	private void setRoom(Exam exam, Period periodToPlan) {
		Room room = roomService.getAvailableRoom(exam.getBeginDateExam(), exam.getEndDateExam(), periodToPlan.getId());
		exam.setRoom(room);
		examService.updateExam(exam);
	}

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
		try {
			return periodRepository.findById(id).get();
		}catch(Exception exception) {
			return null;
		}
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











