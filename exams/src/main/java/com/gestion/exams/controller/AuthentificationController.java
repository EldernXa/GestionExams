package com.gestion.exams.controller;

import java.security.Principal;

import com.gestion.exams.entity.Student;
import com.gestion.exams.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.gestion.exams.services.AuthentificationService;

@Controller
@CrossOrigin(origins = "http://localhost:10003")
public class AuthentificationController {

	@Autowired
	private AuthentificationService authentificationService;
	@Autowired
	private StudentService studentService;

	@PutMapping(value = "/loginRole")
	public ResponseEntity<String> login(Principal principal){
		String role = authentificationService.getRole(principal.getName());

		return new ResponseEntity<>(role, HttpStatus.OK);
	}

	@GetMapping(value="/loggedMessage")
	public ResponseEntity<String> getLoggedMessage(Principal principal){
		String role = authentificationService.getRole(principal.getName());
		if(role.contentEquals("STUDENT")){
			Student student = studentService.getStudentByEmail(principal.getName());
			return new ResponseEntity<>("Bienvenue "+student.getFirstName()+" "+student.getLastName(), HttpStatus.OK);
		}
		else if(role.contentEquals("ADMIN")){
			return new ResponseEntity<>("Bienvenue "+principal.getName(), HttpStatus.OK);
		}
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
