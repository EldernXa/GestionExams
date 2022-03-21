package com.gestion.exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InscriptionControllerTest {

    @Autowired
    private MockMvc mvc;

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

}
