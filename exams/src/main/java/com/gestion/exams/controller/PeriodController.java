package com.gestion.exams.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.exams.dto.PeriodDTO;
import com.gestion.exams.entity.Period;
import com.gestion.exams.services.PeriodService;

@RestController
@RequestMapping
public class PeriodController {

	@Autowired
	private PeriodService periodService;

	@GetMapping("/periodList/{id}/beginDate")
	public ResponseEntity<String> getBeginDatePeriod(@PathVariable long id){
		String str = periodService.beginDatePeriodToString(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/periodList/{id}/endDate")
	public ResponseEntity<String> getEndDatePeriod(@PathVariable long id){
		String str = periodService.endDatePeriodToString(id);
		if(str != null) {
			return new ResponseEntity<>(str, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/periodList")
	public ResponseEntity<List<PeriodDTO>> getListPeriod(){
		List<PeriodDTO> listPeriodDTO = periodService.getListPeriod();
		return new ResponseEntity<>(listPeriodDTO, HttpStatus.OK);
	}

	@PostMapping("/period")
	public Period postPeriod(@RequestBody Map<String, String> mapPeriod) {
		Period period = periodService.getPeriodFromMap(mapPeriod);
		return periodService.savePeriod(period);
	}

}















