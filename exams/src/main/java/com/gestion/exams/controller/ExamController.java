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
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Exam> addNewExams(@RequestBody Map<String, String> mapNewExam){
		Exam exam = examService.saveNewExam(mapNewExam);
		if(exam != null) {
			return new ResponseEntity<>(exam,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/session/{nameUE}/{idPeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Integer> getNextSessionOfAnExam(@PathVariable String nameUE, @PathVariable long idPeriod){
		try {
			return new ResponseEntity<>(examService.getNextSessionOfAnExam(nameUE, idPeriod), HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Exam>> getAllExams(){
		return new ResponseEntity<>(examService.getAllExams(), HttpStatus.OK);
	}

	@GetMapping("/list/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<ExamDTO>> getAllExamsFromPeriod(@PathVariable long id){
		return new ResponseEntity<>(examService.getAllExamsFromPeriod(id), HttpStatus.OK);
	}

	@GetMapping("/listUE")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<String>> getListUE(){
		List<String> listUE = ueService.getListUeName();
		return new ResponseEntity<>(listUE, HttpStatus.OK);
	}

	@GetMapping("/listUE/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<String>> getListUEThatAreNotInAPeriod(@PathVariable long id){
		List<String> listUE = new ArrayList<>();
		for(UE ue : ueService.getAllUE()) {
			if(!periodService.verifyIfExamCanBeAddedToAPeriod(ue, id)) {
				listUE.add(ue.getName());
			}
		}
		return new ResponseEntity<>(listUE, HttpStatus.OK);
	}

	@GetMapping("/{id}/nameUE")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> getNameUEExam(@PathVariable long id){
		String str = examService.getNameUE(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/isFinish/{idExam}/{idPeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> isFinish(@PathVariable long idExam, @PathVariable long idPeriod){
		try {
			boolean isFinished = examService.isExamFinished(idExam, idPeriod);
			return new ResponseEntity<>(isFinished, HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{idExam}/student")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> hasStudent(@PathVariable long idExam){
		try {
			boolean hasStudent = examService.hasStudent(idExam);
			return new ResponseEntity<>(hasStudent, HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}

























