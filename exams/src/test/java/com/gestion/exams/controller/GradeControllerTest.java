package com.gestion.exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.dto.UeDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.repository.ExamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GradeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ExamRepository examRepository;

    String Admintoken;

    String studentToken;

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
        Admintoken = response.get("access_token");
        System.out.println(Admintoken);

        MvcResult  mvcResult2 =  mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "student1@noteplus.fr")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andReturn();
        String json2 = mvcResult2.getResponse().getContentAsString();
        Map<String, String> response2 = new ObjectMapper().readValue(json2, Map.class);
        studentToken = response2.get("access_token");
        System.out.println(studentToken);
    }

    @Test
    public void getGradesByExamTest() throws Exception {
        Exam exam  = examRepository.findAll().get(0);
        mvc.perform(get("/grades/exam/"+ exam.getIdExam()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + Admintoken))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void getGradesOfStudentTest() throws Exception {
        mvc.perform(get("/grades/student").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + studentToken))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    @Transactional
    public void updateAllGradesTest() throws Exception {
       /* Exam exam  = examRepository.findAll().get(0);
        mvc.perform(post("/grades/exams/"+ exam.getIdExam()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + Admintoken))
                        .andExpect(status().isOk())
                        .andReturn();*/
    }

    @Test
    public void updateGradeTest() throws Exception {
        /*Exam exam  = examRepository.findAll().get(0);
        mvc.perform(post("/grades/exam/"+ exam.getIdExam()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + Admintoken))
                .andExpect(status().isOk())
                .andReturn();*/
    }

    @Test
    public void deleteGradeTest() throws Exception {
        /*Exam exam  = examRepository.findAll().get(1);
        mvc.perform(delete("/grades/exam/"+ exam.getIdExam()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + Admintoken))
                        .andExpect(status().isOk());*/
    }

}
