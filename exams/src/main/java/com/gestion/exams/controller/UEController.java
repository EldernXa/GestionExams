package com.gestion.exams.controller;

import com.gestion.exams.dto.UeDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.services.ExamService;
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
import java.util.Calendar;
import java.util.Date;
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


	@GetMapping("/allUE")
	//@PreAuthorize("hasAuthority('STUDENT')")
	public List<UeDTO> getAllUE(){
		ModelMapper modelMapper = new ModelMapper();
		List<UE> listUe = ueService.getAllUE();
		return listUe.stream().map(ue -> modelMapper.map(ue, UeDTO.class)).collect(Collectors.toList());
	}

	@GetMapping("/{name}")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
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
		System.out.println(name + " deleted (controller back)");
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
		UE ueToBeUpdated = ueService.getUeByName(name);
		ueService.updateUE(ue, name);
		return modelMapper.map(ue,UeDTO.class);
	}

	@GetMapping("/subscribeable/{year}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public List<UE> getSubscribeableInscriptionsOfStudent(Principal principal, @PathVariable int year){ //student will be replaced with Principal
		Student student = studentService.getStudentByEmail(principal.getName());
		List<UE> all_ues = ueService.getAllUE();
		List<Inscription> student_inscriptions = student.getInscriptions();
		List<UE> subscribeable_ues = new ArrayList<>();
		Date currentDate = Calendar.getInstance().getTime();
		for(UE ue : all_ues) {
			boolean isSubscribeable = true;
			List<Exam> exams_of_ue_during_year = examService.getExamsByUeAndYear(ue,year);
			for (Inscription i : student_inscriptions) {
				if (i.getUe().getName() == ue.getName() && i.getYear() == year) {
					isSubscribeable = false;
				}
			}
			for(Exam e : exams_of_ue_during_year){
				if(e.getBeginDateExam()!=null) {
					isSubscribeable = false;
				}
			}
			if(isSubscribeable) {
				subscribeable_ues.add(ue);
			}
		}
		return subscribeable_ues;
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
