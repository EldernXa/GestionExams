package com.gestion.exams.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;

import com.gestion.exams.services.AuthentificationService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class AuthentificationController {

	@Autowired
	private AuthentificationService authentificationService;

	@PutMapping(value = "/loginRole")
	//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<String> login(Principal principal){
		System.err.println("okok");
		String role = authentificationService.getRole(principal.getName());

		return new ResponseEntity<>(role, HttpStatus.OK);
	}

}
