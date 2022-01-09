package com.gestion.exams;

import com.gestion.exams.entity.Discipline;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.UEService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class UeServiceTest {
    @InjectMocks
    UEService ueService;
    @Mock
    private UERepository ueRepository;

    UE ue1 = new UE();
    UE ue2 = new UE();

    @BeforeAll
    public void initUeForTest(){
        ue1.setName("ASR");
        ue1.setCredit(3);
        ue1.setDurationExam(2);
        ue1.setDiscipline(Discipline.INFORMATIQUE);

        ue2.setName("Analyse");
        ue2.setCredit(6);
        ue2.setDurationExam(2);
        ue2.setDiscipline(Discipline.MATH);

        ueRepository.save(ue1);
        ueRepository.save(ue2);
    }

    @org.junit.Test
    public void createUeTest(){
        when(ueRepository.save(ArgumentMatchers.any(UE.class))).thenReturn(ue1);
        UE createdUE = ueService.createUE(ue1);
        assertEquals(createdUE.getName(), ue1.getName());
        verify(ueRepository).save(ue1);
    }

    @org.junit.Test
    public void updateUeTest(){

    }

    @Test
    public void getAllUeTest(){
        List<UE> ues = new ArrayList<>();
        ues.add(ue1);
        ues.add(ue2);
        given(ueRepository.findAll()).willReturn(ues);
        List<UE> expected  = ueService.getAllUE();
        assertEquals(expected, ues);
        verify(ueRepository).findAll();
    }

    @Test
    public void getUeByNameTest(){

    }

    @Test
    public void deleteUeTest(){

    }
}
