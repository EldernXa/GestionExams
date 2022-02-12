package com.gestion.exams.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
		initPeriod(id);
		for(Exam exam : listExamFromAPeriod) {
			Date dateBeginWithHour = DateService.createDate(String.valueOf(DateService.getDay(dateBegin)),
					String.valueOf(DateService.getMonth(dateBegin)), String.valueOf(DateService.getYear(dateBegin)), "08");
			Date newDate = lastDateAvailable(periodToPlan, exam, dateBeginWithHour, DateService.addHours(dateBeginWithHour, exam.getUe().getDurationExam()));
			if(newDate != null) {
				dateBeginWithHour = newDate;
			}

			dateBeginWithHour = getBestDateForAnExam(dateBeginWithHour, newDate, periodToPlan, exam);
			// TODO if not room available repeat loop for date
			changeExam(exam, dateBeginWithHour, DateService.addHours(dateBeginWithHour, exam.getUe().getDurationExam()), periodToPlan);
		}

		return convertToDTO(periodToPlan);
	}

	public Period getNextPeriod() {
		Period nextPeriod = null;

		Date todayDate = Calendar.getInstance().getTime();

		for(Period period : periodRepository.findAll()) {
			if((nextPeriod == null && period.getBeginDatePeriod().after(todayDate)) ||
					(nextPeriod!=null && period.getBeginDatePeriod().after(todayDate) && period.getBeginDatePeriod().before(nextPeriod.getBeginDatePeriod()))) {
				nextPeriod = period;
			}
		}

		return nextPeriod;
	}

	private Date getBestDateForAnExam(Date initDate, Date currentLastDateAvailable, Period periodToPlan, Exam exam) throws ParseException {
		boolean canPassNoon = false;
		Date dateToReturn = initDate;
		Date newDate = currentLastDateAvailable;

		while(newDate != null && newDate.before(periodToPlan.getEndDatePeriod())) {

			Map<Integer, Object> mapForTheTurn = oneInstanceForGettingDateAvailable(newDate, dateToReturn, periodToPlan, exam, canPassNoon);
			newDate = (Date)mapForTheTurn.get(1);
			dateToReturn = (Date)mapForTheTurn.get(2);
			canPassNoon = (boolean)mapForTheTurn.get(3);
		}

		return dateToReturn;
	}

	private Map<Integer, Object> oneInstanceForGettingDateAvailable(Date currentLastDateAvailable, Date currentDate, Period periodToPlan, Exam exam, boolean canPassNoon) throws ParseException{
		Date newDate = currentLastDateAvailable;
		Date dateToReturn = currentDate;
		Date saveDate;
		newDate = lastDateAvailable(periodToPlan, exam, newDate, DateService.addHours(newDate, exam.getUe().getDurationExam()));
		if(newDate != null) {
			dateToReturn = newDate;
		}
		saveDate = dateToReturn;
		if(newDate == null) {
			newDate = correctDateBetweenNoon(dateToReturn, DateService.addHours(dateToReturn, exam.getUe().getDurationExam()));
			if(newDate != null && !canPassNoon) {
				dateToReturn = newDate;
			}
			else {
				newDate = correctDateInTheEndOfTheDay(dateToReturn, DateService.addHours(dateToReturn, exam.getUe().getDurationExam()));
				if(newDate != null) {
					dateToReturn = newDate;
				}
			}
			if(saveDate.compareTo(dateToReturn) == 0) {
				canPassNoon = true;
			}
		}

		HashMap<Integer, Object> mapToReturn = new HashMap<>();
		mapToReturn.put(1, newDate);
		mapToReturn.put(2, dateToReturn);
		mapToReturn.put(3, canPassNoon);
		return mapToReturn;

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
			return DateService.getAfterNoonOfADate(beginDate);
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


	private void changeExam(Exam exam, Date beginDate, Date endDate, Period periodToPlan) throws ParseException {
		exam.setBeginDateExam(beginDate);
		exam.setEndDateExam(endDate);
		setRoom(exam, periodToPlan);
		examService.updateExam(exam);
	}

	private void setRoom(Exam exam, Period periodToPlan) throws ParseException {
		Room room = roomService.getAvailableRoom(exam.getBeginDateExam(), exam.getEndDateExam(), periodToPlan.getId());
		exam.setRoom(room);
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

	private void initPeriod(long idPeriod) {
		Period periodToInit = periodRepository.getById(idPeriod);
		List<Exam> listExamFromAPeriod = periodToInit.getExams();
		for(Exam exam : listExamFromAPeriod) {
			exam.setRoom(null);
			exam.setBeginDateExam(null);
			exam.setEndDateExam(null);
			examService.updateExam(exam);
		}
	}

}











