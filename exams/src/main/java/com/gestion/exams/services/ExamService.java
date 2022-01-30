package com.gestion.exams.services;

import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.UE;
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

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UERepository ueRepository;

	public List<Exam> getAllExams(){
		return examRepository.findAll();
	}

	public Optional<Exam> getExamById(long idExam){
		return examRepository.findById(idExam);
	}

	public List<ExamDTO> getAllExamsFromPeriod(long id){
		Period period = periodRepository.findById(id).get();
		List<Exam> listExam = period.getExams();
		List<ExamDTO> listExamDTO = new ArrayList<>();
		for(Exam exam : listExam) {
			listExamDTO.add(convertToDTO(exam));
		}
		if(listExamDTO.size()>0) {
			try {
				System.err.println(listExamDTO.get(0).getBeginDateExam());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listExamDTO;
	}

	public ExamDTO convertToDTO(Exam exam) {
		ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
		examDTO.setUe(exam.getUe().getName());
		examDTO.setBeginDateExam(null);
		examDTO.setEndDateExam(null);
		examDTO.setNameRoom("Pas de salle pour l'instant");
		return examDTO;
	}

	public Exam convertToEntity(ExamDTO examDTO) {
		Exam exam = modelMapper.map(examDTO, Exam.class);
		exam.setIdExam(examDTO.getIdExam());
		exam.setUe(ueRepository.findById(examDTO.getUe()).get());
		return exam;
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
		Period period = periodRepository.findById(Long.parseLong(mapExam.get("idPeriod"))).get();
		UE ue = ueRepository.findById(mapExam.get("ue")).get();
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
