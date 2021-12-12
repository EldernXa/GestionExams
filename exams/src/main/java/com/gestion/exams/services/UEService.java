package com.gestion.exams.services;

import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.UERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UEService {

    @Autowired
    UERepository ueRepository;

    @Transactional
    public UE createUE(UE ue){
        return ueRepository.save(ue);
    }

    @Transactional
    public UE updateUE(UE ue, UE ue1){
        ue.setName(ue1.getName());
        ue.setCredit(ue1.getCredit());
        ue.setDurationExam(ue1.getDurationExam());
        ue.setDiscipline(ue1.getDiscipline());
        return ueRepository.save(ue);
    }

    @Transactional
    public void deleteUE(String name){
        UE ue = ueRepository.getUEByName(name);
        ueRepository.delete(ue);
    }

    public List<UE> getAllUE(){
        return ueRepository.findAll();
    }

    public UE getUeByName(String name){
        UE ue = ueRepository.getUEByName(name);
        return ue;
    }
}
