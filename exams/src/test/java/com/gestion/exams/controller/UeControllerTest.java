package com.gestion.exams.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.dto.UeDTO;
import com.gestion.exams.entity.Discipline;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.UEService;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.ResultMatcher;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UeControllerTest{

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UEService ueService;

    @MockBean
    UERepository ueRepository;

    String token;

   static private UE ue1;
   ObjectMapper mapper = new ObjectMapper();
   ModelMapper modelMapper = new ModelMapper();

    @Before
    public void authenticate() throws Exception {
        Map<String, String> user = Map.of(
                "email", "school1@noteplus.fr",
                "password", "password2"
        );
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

    @BeforeClass
    public static void initUeForTest() {
        ue1 = new UE();
        ue1.setName("ASR");
        ue1.setCredit(3);
        ue1.setDurationExam(2);
        ue1.setDiscipline(Discipline.INFORMATIQUE);

    }

    @Test
    @Transactional
    public void getAllUETest() throws Exception {
        List<UE> allUe = List.of(ue1);
        List<UeDTO> expectedUe = allUe.stream().map(ue -> modelMapper.map(ue, UeDTO.class)).collect(Collectors.toList());
        given(ueRepository.findAll()).willReturn(allUe);

        MvcResult mvcResult = mvc.perform(get("/ue/allUE")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        List<UeDTO> myObjects = Arrays.asList(mapper.readValue(result, UeDTO[].class));
        assertThat(myObjects).isEqualTo(expectedUe);
    }

    @Test
    public void test(){
        assertTrue(true);
    }
}
