package com.gestion.exams.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gestion.exams.dto.PeriodDTO;
import com.gestion.exams.entity.Period;
import com.gestion.exams.services.PeriodService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:10003")
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

	@GetMapping("/verifyNamePeriod/{namePeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> isNameGood(@PathVariable String namePeriod){
		return new ResponseEntity<>(!periodService.verifyIfNameIsAlreadyUsed(namePeriod), HttpStatus.OK);
	}

	@GetMapping("/verifyDatePeriod/{dateBegin}/{dateEnd}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Integer> isDateOfPeriodGood(@PathVariable String dateBegin, @PathVariable String dateEnd) throws ParseException{
		return new ResponseEntity<>(periodService.verifyPeriodDateIsGood(dateBegin, dateEnd), HttpStatus.OK);
	}

	@GetMapping("/periodList")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PeriodDTO>> getListPeriod(){
		List<PeriodDTO> listPeriodDTO = periodService.getListPeriod();
		return new ResponseEntity<>(listPeriodDTO, HttpStatus.OK);
	}

	@GetMapping("/period/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PeriodDTO> getPeriod(@PathVariable long id){
		PeriodDTO period = periodService.convertToDTO(periodService.getPeriod(id));
		if(period != null) {
			return new ResponseEntity<>(period, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/period")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Period postPeriod(@RequestBody Map<String, String> mapPeriod) {
		Period period = periodService.getPeriodFromMap(mapPeriod);
		return periodService.savePeriod(period);
	}


	@PutMapping("/period/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PeriodDTO> updatePlanning(@PathVariable long id) throws ParseException{
		PeriodDTO periodToPlan = periodService.planRoomAndDateOfExams(id);
		if(periodToPlan != null) {
			return new ResponseEntity<>(periodToPlan, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/period/{idPeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deletePeriod(@PathVariable long idPeriod) {
		periodService.deletePeriod(idPeriod);
	}

	@GetMapping("/period/verifyPlanify/{idPeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> verifyIfPeriodIsPlanify(@PathVariable long idPeriod){
		try {
			return new ResponseEntity<>(periodService.verifyIfAPeriodIsPlanify(idPeriod), HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/period/undoPlanify/{idPeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> verifyIfPeriodCanBeUndone(@PathVariable long idPeriod){
		try {
			return new ResponseEntity<>(periodService.verifyIfAPeriodCanBeUndone(idPeriod), HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/period/initPeriod/{idPeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void reinitPlanning(@PathVariable long idPeriod){
		periodService.initPeriod(idPeriod);
	}

	@GetMapping("/period/isFinished/{idPeriod}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> isPeriodFinished(@PathVariable long idPeriod) {
		Period period = periodService.getPeriod(idPeriod);
		if(period == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(period.getEndDatePeriod().before(Calendar.getInstance().getTime()), HttpStatus.OK);
	}

}















