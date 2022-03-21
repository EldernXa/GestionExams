package com.gestion.exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.entity.Authentification;
import com.gestion.exams.repository.AuthentificationRepository;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;
    @Autowired
    ExamService examService;
    @Autowired
    AuthentificationRepository authentificationRepository;

    String token;

    ObjectMapper mapper = new ObjectMapper();

    ModelMapper modelMapper = new ModelMapper();



    @Before
    public void authenticate() throws Exception {
      MvcResult  mvcResult =  mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "student1@noteplus.fr")
                        .param("password", "password"))
                        .andExpect(status().isOk())
                        .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        Map<String, String> response = new ObjectMapper().readValue(json, Map.class);
        token = response.get("access_token");
        System.out.println(token);
    }

    @Test
    public void getNextPeriodOfExamTest() throws Exception {
        mvc.perform(get("/student/exams").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void getAuthTest() throws Exception {
        mvc.perform(get("/student/auth").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();
    }

}
