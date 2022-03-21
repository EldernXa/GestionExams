package com.gestion.exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.exams.dto.UeDTO;
import com.gestion.exams.entity.Discipline;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.StudentService;
import com.gestion.exams.services.UEService;
import org.junit.Before;
import org.junit.BeforeClass;
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
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UeControllerTest{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UEService ueService;

    @MockBean
    private StudentService studentService;

    @MockBean
    UERepository ueRepository;

    String token;

   static private UE ue1;
   ObjectMapper mapper = new ObjectMapper();
   ModelMapper modelMapper = new ModelMapper();

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
        given(ueService.getAllUE()).willReturn(allUe);

        MvcResult mvcResult = mvc.perform(get("/ue/allUE")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        List<UeDTO> myObjects = Arrays.asList(mapper.readValue(result, UeDTO[].class));
        assertThat(myObjects).isEqualTo(expectedUe);
    }

    @Test
    public void getUeByNameTest() throws Exception {
        UeDTO ueDTO = modelMapper.map(ue1,UeDTO.class);
        given(ueService.getUeByName(ue1.getName())).willReturn(ue1);

        MvcResult mvcResult = mvc.perform(get("/ue/"+ ue1.getName())
                        .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        UeDTO ueDTO1 = mapper.readValue(result, UeDTO.class);
        assertThat(ueDTO1.getName()).isEqualTo(ueDTO.getName());
    }

    @Test
    @Transactional
    public void deleteUeTest() throws Exception {
        doNothing().when(ueService).deleteUE(ue1.getName());
        mvc.perform(delete("/ue/"+ ue1.getName())
                        .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
                        .andExpect(status().isNoContent())
                        .andReturn();
    }

    @Test
    public void addNewUeTest() throws Exception {
        UeDTO ueDTOToBeCreated = modelMapper.map(ue1,UeDTO.class);
        given(ueService.createUE(ue1)).willReturn(ue1);
        MvcResult mvcResult = mvc.perform(post("/ue/add")
                        .content(mapper.writeValueAsString(ue1))
                        .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        UeDTO ueDTOCreated = mapper.readValue(result, UeDTO.class);
        assertThat(ueDTOCreated.getName()).isEqualTo(ueDTOToBeCreated.getName());
    }

    @Test
    public void updateUeTest() throws Exception {
        UeDTO ueDTOToBeUpdated = modelMapper.map(ue1,UeDTO.class);
        given(ueService.updateUE(ue1, ue1.getName())).willReturn(ue1);
        MvcResult mvcResult = mvc.perform(put("/ue/update/"+ue1.getName())
                        .content(mapper.writeValueAsString(ue1))
                        .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        UeDTO ueDTOUpdate = mapper.readValue(result, UeDTO.class);
        assertThat(ueDTOUpdate.getName()).isEqualTo(ueDTOToBeUpdated.getName());
    }

    @Test
    public void getSubscribeableInscriptionsOfStudentTest(){
        //TO DO
    }
}
