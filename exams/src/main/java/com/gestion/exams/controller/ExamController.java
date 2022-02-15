package com.gestion.exams.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.UE;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.PeriodService;
import com.gestion.exams.services.UEService;

@Controller
@RequestMapping("/exam")
@CrossOrigin(origins = "http://localhost:4200")
public class ExamController {

	@Autowired
	private ExamService examService;

	@Autowired
	private PeriodService periodService;

	@Autowired
	private UEService ueService;

	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Exam> addNewExams(@RequestBody Map<String, String> mapNewExam){
		Exam exam = examService.saveNewExam(mapNewExam);
		if(exam != null) {
			return new ResponseEntity<>(exam,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Exam>> getAllExams(){
		return new ResponseEntity<>(examService.getAllExams(), HttpStatus.OK);
	}

	@GetMapping("/list/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<List<ExamDTO>> getAllExamsFromPeriod(@PathVariable long id){
		return new ResponseEntity<>(examService.getAllExamsFromPeriod(id), HttpStatus.OK);
	}

	@GetMapping("/{id}/beginDate")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<String> getBeginDateExam(@PathVariable long id){
		String str = examService.getBeginDateExam(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}/endDate")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<String> getEndDateExam(@PathVariable long id){
		String str = examService.getEndDateExam(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}/fullBeginDate")
	public ResponseEntity<String> getBeginFullDate(@PathVariable long id){
		String str = examService.getFullBeginDateExam(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}/fullEndDate")
	public ResponseEntity<String> getEndFullDate(@PathVariable long id){
		String str = examService.getFullEndDateExam(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/listUE")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<List<String>> getListUE(){
		List<String> listUE = ueService.getListUeName();
		return new ResponseEntity<>(listUE, HttpStatus.OK);
	}

	@GetMapping("/listUE/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<List<String>> getListUEThatAreNotInAPeriod(@PathVariable long id){
		List<String> listUE = new ArrayList<>();
		for(UE ue : ueService.getAllUE()) {
			if(!periodService.verifyIfExamAlreadyExist(ue, id)) {
				listUE.add(ue.getName());
			}
		}
		return new ResponseEntity<>(listUE, HttpStatus.OK);
	}

	@GetMapping("/{id}/nameUE")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<String> getNameUEExam(@PathVariable long id){
		String str = examService.getNameUE(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}











