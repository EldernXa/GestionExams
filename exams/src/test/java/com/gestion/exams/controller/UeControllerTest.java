package com.gestion.exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.entity.Discipline;
import com.gestion.exams.entity.UE;
import com.gestion.exams.services.UEService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UeControllerTest{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UEService ueService;

    String token;

   static UE ue1 = new UE();

    @Before
    public void authenticate() throws Exception {
        Map<String, String> user = Map.of(
                "email", "emailSchool0",
                "password", "password20"
        );
       MvcResult mvcResult =  mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "email0")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        Map<String, String> response = new ObjectMapper().readValue(json, Map.class);
        token = response.get("access_token");
        System.out.println(token);
    }

    @BeforeAll
    public static void initUeForTest() {
        ue1.setName("ASR");
        ue1.setCredit(3);
        ue1.setDurationExam(2);
        ue1.setDiscipline(Discipline.INFORMATIQUE);
    }

    @Test
    @Transactional
    public void getAllUETest() throws Exception {
       List<UE> allUe = Arrays.asList(ue1);
        given(ueService.getAllUE()).willReturn(allUe);

        mvc.perform(get("/ue/allUE")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization", "bearer " + token))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is(ue1.getName())));
    }

    @Test
    public void test(){
        assertTrue(true);
    }
}
