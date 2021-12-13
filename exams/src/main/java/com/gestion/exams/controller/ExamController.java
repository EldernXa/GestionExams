package com.gestion.exams.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

}
