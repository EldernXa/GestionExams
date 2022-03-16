package com.gestion.exams.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.UERepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private PeriodRepository periodRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UERepository ueRepository;

	private String msgNotPlannedYet = "Pas planifiée pour l'instant";

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

	public String getBeginDateExam(long id) {
		try {
			Exam exam = examRepository.getById(id);
			if(exam.getBeginDateExam()==null) {
				return msgNotPlannedYet;
			}
			return DateService.convertDateClassToStringDate(exam.getBeginDateExam());
		}catch(Exception exception) {
			return null;
		}
	}

	public String getEndDateExam(long id) {
		try {
			Exam exam = examRepository.getById(id);
			if(exam.getEndDateExam() == null) {
				return msgNotPlannedYet;
			}
			return DateService.convertDateClassToStringDate(exam.getEndDateExam());
		}catch(Exception exception) {
			return null;
		}
	}

	public String getFullBeginDateExam(long id) {
		try {
			Exam exam = examRepository.getById(id);
			if(exam.getBeginDateExam() == null) {
				return msgNotPlannedYet;
			}
			return DateService.convertDateClassToFullStringDate(exam.getBeginDateExam());
		}catch(Exception exception) {
			return null;
		}
	}

	public String getFullEndDateExam(long id) {
		try {
			Exam exam = examRepository.getById(id);
			if(exam.getEndDateExam() == null) {
				return msgNotPlannedYet;
			}
			return DateService.convertDateClassToFullStringDate(exam.getEndDateExam());
		}catch(Exception exception) {
			return null;
		}
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

}
