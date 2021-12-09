package com.gestion.exams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.exams.entity.Period;
import com.gestion.exams.services.PeriodService;

@Controller
@RequestMapping("/period_list")
public class PeriodController {

	@Autowired
	private PeriodService periodService;

	@GetMapping
	public ResponseEntity<List<Period>> getListPeriod(){
		return new ResponseEntity<>(periodService.getListPeriod(), HttpStatus.OK);
	}

}
