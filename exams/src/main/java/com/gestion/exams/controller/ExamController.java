package com.gestion.exams.controller;

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

import com.gestion.exams.entity.Exam;
import com.gestion.exams.services.ExamService;

@Controller
@RequestMapping("/exam")
public class ExamController {

	@Autowired
	private ExamService examService;

	@PostMapping("/add")
	public ResponseEntity<Exam> addNewExams(@RequestBody Map<String, String> mapNewExam){
		Exam exam = examService.saveNewExam(mapNewExam);
		if(exam != null) {
			return new ResponseEntity<>(exam,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Exam>> getAllExams(){
		return new ResponseEntity<>(examService.getAllExams(), HttpStatus.OK);
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

	@GetMapping("/{id}/nameUE")
	public ResponseEntity<String> getNameUEExam(@PathVariable long id){
		String str = examService.getNameUE(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}











