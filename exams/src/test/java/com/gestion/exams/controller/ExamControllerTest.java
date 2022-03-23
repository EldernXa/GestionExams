package com.gestion.exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Period;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.ExamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExamControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    UERepository ueRepository;
    @MockBean
    ExamService examService;

    static private Exam exam;

    String token;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void authenticate() throws Exception {
        MvcResult mvcResult =  mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "school1@noteplus.fr")
                        .param("password", "password2"))
                .andExpect(status().isOk())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        Map<String, String> response = new ObjectMapper().readValue(json, Map.class);
        token = response.get("access_token");
        System.out.println(token);
    }

    @Test
    public void addNewExamsNullTest() throws Exception {
        mvc.perform(post("/exam/add").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                        .andExpect(status().isBadRequest())
                        .andReturn();
    }

    @Test
    public void getNextSessionOfAnExamTest() throws Exception {
        Exam exam = examRepository.findAll().get(1);
        String nameUe = exam.getUe().getName();
        long idPeriod  = exam.getPeriod().getId();
        Optional<Period> period = periodRepository.findById(idPeriod);
        mvc.perform(get("/exam/session/"+nameUe+"/"+period.get().getId()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void getAllExams() throws Exception {
        mvc.perform(get("/exam/list").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllExamsFromPeriodTest() throws Exception {
        Exam exam = examRepository.findAll().get(1);
        long idPeriod  = exam.getPeriod().getId();
        Optional<Period> period = periodRepository.findById(idPeriod);
        mvc.perform(get("/exam/list/"+period.get().getId()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void getListUETest() throws Exception {
        mvc.perform(get("/exam/listUE").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getListUEThatAreNotInAPeriodTest() throws Exception {
        Exam exam = examRepository.findAll().get(1);
        long idPeriod  = exam.getPeriod().getId();
        Optional<Period> period = periodRepository.findById(idPeriod);
        mvc.perform(get("/exam/listUE/"+period.get().getId()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void hasStudentTest() throws Exception {
        Exam exam = examRepository.findAll().get(0);
        mvc.perform(get("/exam/"+exam.getIdExam()+"/student").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
    }

}
