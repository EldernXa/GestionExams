package com.gestion.exams.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.gestion.exams.entity.Period;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.services.DateService;

@SpringBootTest
@AutoConfigureMockMvc
class PeriodControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PeriodRepository periodRepository;

	@Test
	void testGetListPeriod() throws Exception {
		mockMvc.perform(get("/periodList"))
		.andExpect(status().isOk());
	}

	@Test
	void testGetBeginDatePeriod() throws Exception{
		Period period = periodRepository.findAll().get(0);
		long id = period.getId();
		String beginDatePeriod = DateService.convertDateClassToStringDate(period.getBeginDatePeriod());
		mockMvc.perform(get("/periodList/"+id+"/beginDate"))
		.andExpect(status().isOk())
		.andExpect(content().string(beginDatePeriod));
	}

	@Test
	void testGettingBeginDatePeriodWithPeriodWhoDoesntExist() throws Exception {
		mockMvc.perform(get("/periodList/0/beginDate"))
		.andExpect(status().isBadRequest());
	}

	@Test
	void testGetEndDatePeriod() throws Exception {
		Period period = periodRepository.findAll().get(0);
		long id = period.getId();
		String endDatePeriodInString = DateService.convertDateClassToStringDate(period.getEndDatePeriod());
		mockMvc.perform(get("/periodList/"+id+"/endDate"))
		.andExpect(status().isOk())
		.andExpect(content().string(endDatePeriodInString));
	}

	@Test
	void testGettingEndDatePeriodWithPeriodWhoDoesntExist() throws Exception {
		mockMvc.perform(get("/periodList/0/endDate"))
		.andExpect(status().isBadRequest());
	}


}








