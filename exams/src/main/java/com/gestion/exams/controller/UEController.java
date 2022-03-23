package com.gestion.exams.controller;

import com.gestion.exams.dto.UeDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.GradeService;
import com.gestion.exams.services.StudentService;
import com.gestion.exams.services.UEService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ue")
@CrossOrigin(origins = "http://localhost:4200")
public class UEController {

	@Autowired
	UEService ueService;
	@Autowired
	StudentService studentService;
	@Autowired
	ExamService examService;
	@Autowired
	GradeService gradeService;


	@GetMapping("/allUE")
	public List<UeDTO> getAllUE(){
		ModelMapper modelMapper = new ModelMapper();
		List<UE> listUe = ueService.getAllUE();
		return listUe.stream().map(ue -> modelMapper.map(ue, UeDTO.class)).collect(Collectors.toList());
	}

	@GetMapping("/{name}")
	public UeDTO getUeByName(@PathVariable String name){
		ModelMapper modelMapper = new ModelMapper();
		UE ue = ueService.getUeByName(name);
		return modelMapper.map(ue,UeDTO.class);
	}

	@DeleteMapping("/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUe(@PathVariable String name){
		ueService.deleteUE(name);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public UeDTO addNewUe(@RequestBody UE ue){
		ueService.createUE(ue);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(ue, UeDTO.class);
	}

	@Transactional
	@PutMapping("/update/{name}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public UeDTO updateUe(@PathVariable String name, @RequestBody UE ue){
		ModelMapper modelMapper = new ModelMapper();
		ueService.updateUE(ue, name);
		return modelMapper.map(ue,UeDTO.class);
	}

	@GetMapping("/subscribeable/{year}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public List<UE> getSubscribeableInscriptionsOfStudent(Principal principal, @PathVariable int year){ //student will be replaced with Principal
		Student student = studentService.getStudentByEmail(principal.getName());
		List<UE> allUes = ueService.getAllUE();
		List<Inscription> studentInscriptions = student.getInscriptions();
		List<UE> subscribeableUes = new ArrayList<>();
		for(UE ue : allUes) {
			boolean isSubscribeable = true;
			List<Exam> examsOfUeDuringYear = examService.getExamsByUeAndYear(ue,year);
			for (Inscription i : studentInscriptions) {
				if (i.getUe().getName().equals(ue.getName()) && i.getYear() == year) {
					isSubscribeable = false;
				}
			}
			for(Exam e : examsOfUeDuringYear) {
				if (e.getBeginDateExam() != null) {
					isSubscribeable = false;
					break;
				}
			}

			if(!gradeService.getGradesMoreThan10ByStudentAndUE(student.getIdStudent(),ue.getName()).isEmpty())
				isSubscribeable = false;
			if(isSubscribeable) {
				subscribeableUes.add(ue);
			}
		}
		return subscribeableUes;
	}

	@GetMapping("/testadmin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String testAdmin(){
		return "Admin is connected";
	}


	@GetMapping("/teststudent")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public String testStudent(){
		return "Student is connected";
	}

	@GetMapping("/accessall")
	public String testAccessAll(){
		return "Anyone is connected";
	}

	@GetMapping("/isUeNameGood/{nameUE}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> isUeNameGood(@PathVariable String nameUE){
		for(UE ue : ueService.getAllUE()) {
			if(ue.getName().contentEquals(nameUE)) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}


}
