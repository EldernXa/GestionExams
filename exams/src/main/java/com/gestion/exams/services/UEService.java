package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.GradePK;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.UERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UEService {

    @Autowired
    UERepository ueRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    GradeRepository gradeRepository;

    @Transactional
    public UE createUE(UE ue){
        return ueRepository.save(ue);
    }

    @Transactional
    public UE updateUE(UE ue, String name){
        UE ue1 = ueRepository.getUEByName(name);
        ue1.setName(ue.getName());
        ue1.setCredit(ue.getCredit());
        ue1.setDurationExam(ue.getDurationExam());
        ue1.setDiscipline(ue.getDiscipline());
        return ueRepository.save(ue1);
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
