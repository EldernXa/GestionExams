package com.gestion.exams.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.PeriodService;

@Controller
@RequestMapping("/exam")
public class ExamController {

	@Autowired
	private ExamService examService;

	@Autowired
	private PeriodService periodService;

	@Autowired
	private UERepository ueRepository; // TODO to remove.

	@PostMapping("/add")
	public ResponseEntity<Exam> addNewExams(@RequestBody Map<String, String> mapNewExam){
		Exam exam = examService.saveNewExam(mapNewExam);
		if(exam != null) {
			System.err.println("okok");
			return new ResponseEntity<>(exam,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Exam>> getAllExams(){
		return new ResponseEntity<>(examService.getAllExams(), HttpStatus.OK);
	}

	@GetMapping("/list/{id}")
	public ResponseEntity<List<ExamDTO>> getAllExamsFromPeriod(@PathVariable long id){
		return new ResponseEntity<>(examService.getAllExamsFromPeriod(id), HttpStatus.OK);
	}

	@GetMapping("/{id}/beginDate")
	public ResponseEntity<String> getBeginDateExam(@PathVariable long id){
		String str = examService.getBeginDateExam(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}/endDate")
	public ResponseEntity<String> getEndDateExam(@PathVariable long id){
		String str = examService.getEndDateExam(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/listUE")
	public ResponseEntity<List<String>> getListUE(){
		// TODO to change place
		List<String> listUE = new ArrayList<>();
		for(UE ue : ueRepository.findAll()) {
			listUE.add(ue.getName());
		}
		return new ResponseEntity<>(listUE, HttpStatus.OK);
	}

	@GetMapping("/listUE/{id}")
	public ResponseEntity<List<String>> getListUEThatAreNotInAPeriod(@PathVariable long id){
		List<String> listUE = new ArrayList<>();
		for(UE ue : ueRepository.findAll()) {
			if(!periodService.verifyIfExamAlreadyExist(ue, id)) {
				listUE.add(ue.getName());
			}
		}
		return new ResponseEntity<>(listUE, HttpStatus.OK);
	}

	@GetMapping("/{id}/nameUE")
	public ResponseEntity<String> getNameUEExam(@PathVariable long id){
		String str = examService.getNameUE(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}










