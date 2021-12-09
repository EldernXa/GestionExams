package com.gestion.exams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.exams.entity.Period;
import com.gestion.exams.services.PeriodService;

@Controller
@RequestMapping
public class PeriodController {

	@Autowired
	private PeriodService periodService;

	@GetMapping("/periodList")
	public ResponseEntity<List<Period>> getListPeriod(){
		return new ResponseEntity<>(periodService.getListPeriod(), HttpStatus.OK);
	}

	@GetMapping("/periodList/{id}/beginDate")
	public ResponseEntity<String> getBeginDatePeriod(@PathVariable long id){
		return new ResponseEntity<>(periodService.beginDatePeriodToString(id), HttpStatus.OK);
	}

	@GetMapping("/periodList/{id}/endDate")
	public ResponseEntity<String> getEndDatePeriod(@PathVariable long id){
		return new ResponseEntity<>(periodService.endDatePeriodToString(id), HttpStatus.OK);
	}

}
