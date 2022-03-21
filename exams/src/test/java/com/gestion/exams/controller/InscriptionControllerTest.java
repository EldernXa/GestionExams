package com.gestion.exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.StudentRepository;
import com.gestion.exams.services.InscriptionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InscriptionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InscriptionService inscriptionService;

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
    public void showInscriptionsOfStudentTest() throws Exception {
        mvc.perform(get("/inscription/all").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + studentToken))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void showInscriptionsOfStudentByAdminTest() throws Exception {
        Student george = studentRepository.findAll().get(0);
        mvc.perform(get("/inscription/all/"+ george.getEmail()).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + Admintoken))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void registerStudentToUETest() throws Exception {
        String nameUE = "Introduction à la programmation";
        int year = 2021;
        mvc.perform(post("/inscription/register/"+ year+"/"+ nameUE).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + studentToken))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void registerStudentToUeByAdminTest() throws Exception {
        String nameUE = "Introduction à la programmation";
        int year = 2021;
        String email = "student1@noteplus.fr";
        mvc.perform(post("/inscription/register/"+ email+"/"+ year+"/"+ nameUE).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + Admintoken))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void unsubscribeStudentFromInscriptionTest() throws Exception {
        String nameUE = "Introduction à la programmation";
        int year = 2021;
        mvc.perform(delete("/inscription/unsubscribe/"+ year+"/"+ nameUE).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + studentToken))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    @Transactional
    public void unsubscribeStudentFromInscriptionByAdminTest() throws Exception {
        String nameUE = "Introduction à la programmation";
        int year = 2021;
        String email = "student1@noteplus.fr";
        mvc.perform(delete("/inscription/unsubscribe/"+ email+"/"+ year+"/"+ nameUE).contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + Admintoken))
                .andExpect(status().isOk())
                .andReturn();
    }
}
