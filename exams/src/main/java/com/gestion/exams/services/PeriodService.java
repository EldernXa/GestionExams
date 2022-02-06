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
import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Room;
import com.gestion.exams.entity.Student;
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
			exam.setRoom(null);
			exam.setBeginDateExam(null);
			exam.setEndDateExam(null);
			examService.updateExam(exam);
		}
		for(Exam exam : listExamFromAPeriod) {
			Date dateBeginWithHour = DateService.createDate(String.valueOf(DateService.getDay(dateBegin)),
					String.valueOf(DateService.getMonth(dateBegin)), String.valueOf(DateService.getYear(dateBegin)), "08");
			Date newDate = lastDateAvailable(periodToPlan, exam, dateBeginWithHour, DateService.addHours(dateBeginWithHour, exam.getUe().getDurationExam()));
			if(newDate != null) {
				dateBeginWithHour = newDate;
			}

			boolean canPass = false;
			Date saveDate;

			// TODO Take in consideration date we cannot do exam.
			while(newDate != null && newDate.before(periodToPlan.getEndDatePeriod())) {
				newDate = lastDateAvailable(periodToPlan, exam, newDate, DateService.addHours(newDate, exam.getUe().getDurationExam()));
				if(newDate != null) {
					dateBeginWithHour = newDate;
				}
				saveDate = dateBeginWithHour;
				// Function to verify date and change it if necessary
				if(newDate == null) {
					newDate = correctDateBetweenNoon(dateBeginWithHour, DateService.addHours(dateBeginWithHour, exam.getUe().getDurationExam()));
					if(newDate != null) {
						dateBeginWithHour = newDate;
					}
					else {
						newDate = correctDateInTheEndOfTheDay(dateBeginWithHour, DateService.addHours(dateBeginWithHour, exam.getUe().getDurationExam()));
						if(newDate != null) {
							dateBeginWithHour = newDate;
						}
					}
					if(saveDate.compareTo(dateBeginWithHour) == 0) {
						canPass = true;
					}
					if(canPass) {
						newDate = null;
					}
				}
			}
			exam.setBeginDateExam(dateBeginWithHour);
			exam.setEndDateExam(DateService.addHours(dateBeginWithHour, exam.getUe().getDurationExam()));
			// TODO if not room available repeat loop for date
			setRoom(exam, periodToPlan);
		}

		return convertToDTO(periodToPlan);
	}

	private Date lastDateAvailable(Period period, Exam exam, Date beginDate, Date endDate) throws ParseException {
		Date newBeginDate = DateService.addMinute(beginDate, 1);
		Date dateToReturn = null;
		for(Inscription inscription : exam.getUe().getInscriptions()) {
			Date date = isStudentAvailable(inscription.getStudent(), period, newBeginDate, endDate);
			if (date != null && (dateToReturn == null || dateToReturn.after(date))) {
				dateToReturn = date;
			}
		}
		return dateToReturn;
	}

	private Date isStudentAvailable(Student student, Period period, Date beginDate, Date endDate) {
		for(Exam exam : period.getExams()) {
			if(examService.isExamDateBetweenOtherDate(exam, beginDate, endDate)) {
				for(Inscription inscription : exam.getUe().getInscriptions()) {
					if(inscription.getStudent().getIdStudent() == student.getIdStudent()) {
						return exam.getEndDateExam();
					}
				}
			}
		}

		return null;
	}

	private Date correctDateBetweenNoon(Date beginDate, Date endDate) throws ParseException{
		Date noon = DateService.getNoonOfADate(beginDate);
		Date afterNoon = DateService.addMinute(DateService.getAfterNoonOfADate(beginDate), 1);
		if(DateService.isBetweenDate(noon, afterNoon, beginDate) || DateService.isBetweenDate(noon, afterNoon, endDate) || DateService.isBetweenDate(beginDate, endDate, noon) ||
				DateService.isBetweenDate(beginDate, endDate, afterNoon)) {
			return afterNoon;
		}
		return null;
	}

	private Date correctDateInTheEndOfTheDay(Date beginDate, Date endDate) throws ParseException {
		Date afterDay = DateService.getAfterDayOfADate(beginDate);
		if(beginDate.after(afterDay) || endDate.after(afterDay)) {
			return DateService.getTheDayAfterAt8Hour(beginDate);
		}
		return null;
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











