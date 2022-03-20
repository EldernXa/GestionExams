package com.gestion.exams.jpa;

import com.gestion.exams.entity.Discipline;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.UERepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UeRepositoryTest {
    @Autowired
    UERepository ueRepository;

    @Test
    public void getUEByNameTest(){
        UE ue = new UE("AABD", 3, 2, Discipline.INFORMATIQUE);
        ueRepository.save(ue);
        UE expected = ueRepository.getUEByName(ue.getName());
        assertThat(expected.getName()).isEqualTo(ue.getName());
    }

    @Test
    @Transactional
    public void deleteByNameTest(){
        UE ue1 = new UE("AA", 6, 2, Discipline.INFORMATIQUE);
        ueRepository.save(ue1);
        assertThat(ue1).isNotNull();

        ueRepository.delete(ue1);

        List<UE> ues = ueRepository.findAll();
        assertThat(ues).hasSize(0);
    }

    @Test
    public void getAllUETest(){
        UE ue = new UE("AABD", 3, 2, Discipline.INFORMATIQUE);
        UE ue1 = new UE("AA", 6, 2, Discipline.INFORMATIQUE);
        ueRepository.save(ue);
        ueRepository.save(ue1);
        List<UE> listUE = ueRepository.findAll();
        assertThat(listUE).hasSize(2);

    }
}
