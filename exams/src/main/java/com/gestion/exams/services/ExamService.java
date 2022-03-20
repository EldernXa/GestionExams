package com.gestion.exams.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gestion.exams.entity.*;
import com.gestion.exams.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.UERepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private PeriodRepository periodRepository;

	@Autowired
	private StudentRepository studentRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UERepository ueRepository;

	private String mstNotRoomYet = "Pas de salle pour l'instant";

	public List<Exam> getAllExams(){
		return examRepository.findAll();
	}

	public Optional<Exam> getExamById(long idExam){
		return examRepository.findById(idExam);
	}

	public Exam updateExam(Exam examToUpdate) {
		return examRepository.save(examToUpdate);
	}

	public List<ExamDTO> getAllExamsFromPeriod(long id){
		List<ExamDTO> listExamDTO = new ArrayList<>();
		periodRepository.findById(id).ifPresent(periodValue ->{
			List<Exam> listExam = periodValue.getExams();
			for(Exam exam : listExam) {
				listExamDTO.add(convertToDTO(exam));
			}
		});

		return listExamDTO;
	}

	public List<ExamDTO> getAllExamsForAStudentFromPeriod(long id, Student student){
		List<ExamDTO> listAllExamForAStudent = new ArrayList<>();
		periodRepository.findById(id).ifPresent(periodValue ->{
			List<Exam> listAllExam = periodValue.getExams();
			List<String> verifyListExam = new ArrayList<>();

			for(Exam exam : listAllExam) {
				for(Inscription inscription : exam.getUe().getInscriptions()) {
					if(inscription.getStudent().getEmail().compareTo(student.getEmail())==0 && !verifyListExam.contains(exam.getUe().getName()) &&
							inscription.getYear() == DateService.getYear(periodValue.getBeginDatePeriod()) &&
							inscription.getUe().getName().contentEquals(exam.getUe().getName()) && exam.getBeginDateExam() != null) {
						listAllExamForAStudent.add(convertToDTO(exam));
						verifyListExam.add(exam.getUe().getName());
					}
				}
			}
		} );

		return listAllExamForAStudent;
	}

	public ExamDTO convertToDTO(Exam exam) {
		ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
		examDTO.setUe(exam.getUe().getName());
		if(examDTO.getNameRoom() == null) {
			examDTO.setNameRoom(mstNotRoomYet);
		}
		if(exam.getBeginDateExam()!= null) {
			examDTO.setBeginDateExam(exam.getBeginDateExam());
		}
		if(exam.getEndDateExam() != null) {
			examDTO.setEndDateExam(exam.getEndDateExam());
		}
		return examDTO;
	}

	public boolean isExamDateBetweenOtherDate(Exam exam, Date beginDate, Date endDate) {
		if(beginDate == null || endDate == null || exam.getBeginDateExam() == null || exam.getEndDateExam() == null) {
			return false;
		}
		return DateService.isBetweenDate(exam.getBeginDateExam(), exam.getEndDateExam(), beginDate) ||
				DateService.isBetweenDate(exam.getBeginDateExam(), exam.getEndDateExam(), endDate);
	}

	public Exam convertToEntity(ExamDTO examDTO) {
		Exam exam = modelMapper.map(examDTO, Exam.class);
		exam.setIdExam(examDTO.getIdExam());
		ueRepository.findById(examDTO.getUe()).ifPresent(exam::setUe);
		return exam;
	}

	private Exam getExamFromMap(Map<String, String> mapExam) {
		List<Object> listOfObject = new ArrayList<>();
		periodRepository.findById(Long.parseLong(mapExam.get("idPeriod"))).ifPresent(listOfObject::add );

		ueRepository.findById(mapExam.get("ue")).ifPresent(listOfObject::add);
		Period period = (Period)listOfObject.get(0);
		UE ue = (UE)listOfObject.get(1);
		return new Exam(null, null, Integer.parseInt(mapExam.get("session")),
				Integer.parseInt(mapExam.get("year")) , null, period, ue);


	}

	public String getNameUE(long id) {
		try {
			Exam exam = examRepository.getById(id);
			return exam.getUe().getName();
		}catch(Exception exception) {
			return null;
		}
	}

	public Exam saveNewExam(Map<String, String> mapExam) {
		Exam exam = getExamFromMap(mapExam);
		return examRepository.save(exam);

	}

	public List<Exam> getExamsByUeAndYear(UE ue, int year){
		return examRepository.searchExamsByUeAndYear(ue,year);

	}

	public int getNextSessionOfAnExam(String nameUE, long idPeriod) {
		Optional<Period> optionalPeriod = periodRepository.findById(idPeriod);
		if(optionalPeriod.isEmpty()) {
			throw new IllegalArgumentException();
		}
		int number = 1;
		for(Exam exam : examRepository.findAll()) {
			if(exam.getUe().getName().contentEquals(nameUE) &&
					(DateService.getYear(optionalPeriod.get().getBeginDatePeriod()) == DateService.getYear(exam.getPeriod().getBeginDatePeriod()))) {
				if(exam.getSession() == 2) {
					return -1;
				}
				if (exam.getSession() == 1) {
					number = 2;
				}
			}
		}
		return number;
	}

	public boolean isExamFinished(long idExam, long idPeriod) {
		Optional<Exam> optionalExam = examRepository.findById(idExam);
		Optional<Period> optionalPeriod = periodRepository.findById(idPeriod);
		if(optionalExam.isEmpty() || optionalPeriod.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Exam exam = optionalExam.get();
		Period period = optionalPeriod.get();
		Date currentDate = Calendar.getInstance().getTime();
		return currentDate.after(period.getEndDatePeriod()) || (exam.getEndDateExam() != null && currentDate.after(exam.getEndDateExam()));
	}

	public boolean hasStudent(long idExam) {
		Optional<Exam> optionalExam = examRepository.findById(idExam);
		if(optionalExam.isEmpty()) {
			throw new IllegalArgumentException();
		}
		List<Student> allStudents = studentRepository.findStudentByExamId(idExam);
		List<Student> students = new ArrayList<>();
		Exam exam = optionalExam.get();
		for(Student s : allStudents) {
			boolean hasMoreThan10 = false;
			if(exam.getSession() == 2){
				List<Grade> grades = s.getGrades();
				for(Grade g : grades) {
					if(g.getGradePK().getExam().getUe().getName().contentEquals(exam.getUe().getName())
							&& g.getGradePK().getExam().getYear() == exam.getYear()
							&& g.getGradePK().getExam().getSession() == 1
							&& g.getValue() >= 10) {
						hasMoreThan10 = true;
					}
				}

			}
			if(!hasMoreThan10) {
				students.add(s);
			}
		}
		return (!students.isEmpty());
	}

}
