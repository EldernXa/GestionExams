package com.gestion.exams.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.RoomRepository;
import com.gestion.exams.repository.UERepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private PeriodRepository periodRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UERepository ueRepository;

	public List<Exam> getAllExams(){
		return examRepository.findAll();
	}

	public String getBeginDateExam(long id) {
		try {
			Exam exam = examRepository.getById(id);
			return DateService.convertDateClassToStringDate(exam.getBeginDateExam());
		}catch(Exception exception) {
			return null;
		}
	}

	public String getEndDateExam(long id) {
		try {
			Exam exam = examRepository.getById(id);
			return DateService.convertDateClassToStringDate(exam.getEndDateExam());
		}catch(Exception exception) {
			return null;
		}
	}

	private Exam getExamFromMap(Map<String, String> mapExam) {
		Period period = periodRepository.findById(Long.parseLong(mapExam.get("namePeriod"))).get();
		UE ue = ueRepository.findById(mapExam.get("nameUE")).get();
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
