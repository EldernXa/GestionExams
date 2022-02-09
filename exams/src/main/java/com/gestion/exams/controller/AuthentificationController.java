package com.gestion.exams.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.gestion.exams.services.AuthentificationService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class AuthentificationController {

	@Autowired
	private AuthentificationService authentificationService;

	@PutMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> mapToLogin){
		String username = mapToLogin.get("username");
		String mdp = mapToLogin.get("mdp");
		String role = authentificationService.getRole(username, mdp);

		return new ResponseEntity<>(role, HttpStatus.OK);
	}

}
