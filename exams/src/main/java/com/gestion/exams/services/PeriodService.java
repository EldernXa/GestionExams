package com.gestion.exams.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.PeriodRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PeriodService {

	@Autowired
	private PeriodRepository periodRepository;

	@Autowired
	private ExamService examService;

	@Autowired
	private ExamRepository examRepository;

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
			newDate = correctDateInTheEndOfTheWeek(dateBeginWithHour);
			if(newDate != null) {
				dateBeginWithHour = newDate;
			}

			dateBeginWithHour = getBestDateForAnExam(dateBeginWithHour, newDate, periodToPlan, exam);

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
			if(inscription.getYear() == DateService.getYear(period.getBeginDatePeriod())) {
				Date date = isStudentAvailable(inscription.getStudent(), period, newBeginDate, endDate);
				if (date != null && (dateToReturn == null || dateToReturn.after(date))) {
					dateToReturn = date;
				}
			}
		}
		return dateToReturn;
	}

	private Date isStudentAvailable(Student student, Period period, Date beginDate, Date endDate) {
		for(Exam exam : period.getExams()) {
			if(examService.isExamDateBetweenOtherDate(exam, beginDate, endDate)) {
				for(Inscription inscription : exam.getUe().getInscriptions()) {
					if(inscription.getYear() == exam.getYear() && inscription.getStudent().getIdStudent() == student.getIdStudent()) {
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

	private Date correctDateInTheEndOfTheWeek(Date beginDate) throws ParseException {
		String dayNameForBeginName = DateService.getDayInString(beginDate);
		if(dayNameForBeginName.contentEquals("samedi") || dayNameForBeginName.contentEquals("dimanche")) {
			return DateService.getTheDayAfterWeekEnd(beginDate);
		}
		return null;
	}


	private void changeExam(Exam exam, Date beginDate, Date endDate, Period periodToPlan) throws ParseException {
		exam.setBeginDateExam(beginDate);
		exam.setEndDateExam(endDate);
		exam.setYear(DateService.getYear(beginDate));
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

	public boolean verifyIfExamCanBeAddedToAPeriod(UE ue, long id) {
		Period period = getPeriod(id);
		if(period.getExams().isEmpty() && examService.getNextSessionOfAnExam(ue.getName(), id) == -1) {
			return true;
		}
		for(Exam exam: period.getExams()) {
			int nextSessionUe = examService.getNextSessionOfAnExam(ue.getName(), id);
			if(exam.getUe().getName().compareTo(ue.getName())==0 || exam.getSession() != nextSessionUe || nextSessionUe == -1) {
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
		periodDTO.setYear(DateService.getYear(period.getBeginDatePeriod()));
		return periodDTO;
	}

	public Period convertToEntity(PeriodDTO periodDTO) throws ParseException{
		Period period = modelMapper.map(periodDTO, Period.class);
		period.setBeginDatePeriod(periodDTO.getBeginDatePeriodInDateFormat());
		period.setEndDatePeriod(periodDTO.getEndDatePeriodInDateFormat());
		period.setId(periodDTO.getId());
		return period;
	}

	public Period getPeriod(long id) {
		try {
			List<Period> period = new ArrayList<>();
			periodRepository.findById(id).ifPresent(period::add);
			return period.get(0);
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
			return null;
		}
	}

	public Period savePeriod(Period periodToSave) {
		return periodRepository.save(periodToSave);
	}

	public void initPeriod(long idPeriod) {
		Period periodToInit = periodRepository.getById(idPeriod);
		List<Exam> listExamFromAPeriod = periodToInit.getExams();
		for(Exam exam : listExamFromAPeriod) {
			exam.setRoom(null);
			exam.setBeginDateExam(null);
			exam.setEndDateExam(null);
			examService.updateExam(exam);
		}
	}

	public int verifyPeriodDateIsGood(String beginDate, String endDate) throws ParseException {
		Date beginDatePeriod = DateService.convertStringDateYearFirstToDateClass(beginDate);
		Date endDatePeriod = DateService.convertStringDateYearFirstToDateClass(endDate);
		for(Period period : periodRepository.findAll()) {
			if(DateService.isBetweenDate(period.getBeginDatePeriod(), period.getEndDatePeriod(), beginDatePeriod)) {
				return 1;
			}
			if(DateService.isBetweenDate(period.getBeginDatePeriod(), period.getEndDatePeriod(), endDatePeriod)) {
				return 2;
			}
		}

		return -1;
	}

	public boolean verifyIfNameIsAlreadyUsed(String namePeriod) {
		for(Period period : periodRepository.findAll()) {
			if(period.getName().contentEquals(namePeriod)) {
				return true;
			}
		}
		return false;
	}

	public void deletePeriod(long idPeriod) {
		Optional<Period> optionalPeriod = periodRepository.findById(idPeriod);
		if(optionalPeriod.isPresent()) {
			examRepository.deleteAll(optionalPeriod.get().getExams());
			periodRepository.deleteById(idPeriod);
		}
	}

	public boolean verifyIfAPeriodIsPlanify(long idPeriod) {
		Optional<Period> optionalPeriod = periodRepository.findById(idPeriod);
		if(optionalPeriod.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Period period = optionalPeriod.get();
		return (!period.getExams().isEmpty() && period.getExams().get(0).getBeginDateExam() != null) ||
				period.getBeginDatePeriod().before(Calendar.getInstance().getTime());
	}

	public boolean verifyIfAPeriodCanBeUndone (long idPeriod) {
		Optional<Period> optionalPeriod = periodRepository.findById(idPeriod);
		if(optionalPeriod.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Period period = optionalPeriod.get();
		if(period.getBeginDatePeriod().before(Calendar.getInstance().getTime())) {
			return false;
		}
		return (!period.getExams().isEmpty() && period.getExams().get(0).getBeginDateExam() != null);
	}

}











