package com.gestion.exams.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Student;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.PeriodService;
import com.gestion.exams.services.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private PeriodService periodService;

	@Autowired
	private ExamService examService;

	@GetMapping("/exams")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public ResponseEntity<List<ExamDTO>> getNextPeriodOfExam(Principal principal) {
		Student currentStudent = studentService.getStudentByEmail(principal.getName());
		Period nextPeriod = periodService.getNextPeriod();
		List<ExamDTO> listExam = examService.getAllExamsForAStudentFromPeriod(nextPeriod.getId(), currentStudent);

		return new ResponseEntity<>(listExam, HttpStatus.OK);
	}

}
