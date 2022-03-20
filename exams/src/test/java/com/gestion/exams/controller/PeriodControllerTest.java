package com.gestion.exams.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.entity.Period;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.services.DateService;

@SpringBootTest
@AutoConfigureMockMvc
public
class PeriodControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

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

	@Test
	void testGettingPeriod() throws Exception{
		Period period = periodRepository.findAll().get(0);
		mockMvc.perform(get("/period/"+period.getId()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("name", is(period.getName())));
	}

	@Test
	void testGettingPeriodWithPeriodWhoDoesntExist() throws Exception {
		mockMvc.perform(get("/period/0"))
		.andExpect(status().isBadRequest());
	}

	@Test
	void testPostPeriod() throws Exception {
		String namePeriod = "period1";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> mapPeriod = new HashMap<>();
		mapPeriod.put("beginDatePeriod", "2022-01-03");
		mapPeriod.put("endDatePeriod", "2022-01-03");
		mapPeriod.put("name", namePeriod);
		String requestedJson = mapper.writeValueAsString(mapPeriod);
		mockMvc.perform(post("/period").contentType(APPLICATION_JSON_UTF8).content(requestedJson))
		.andExpect(status().isOk());
	}


}








