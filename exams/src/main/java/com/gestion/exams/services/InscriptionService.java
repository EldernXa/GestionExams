package com.gestion.exams.services;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.InscriptionRepository;
import com.gestion.exams.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService {

    @Autowired
    InscriptionRepository inscriptionRepository;
    StudentRepository studentRepository;

    public Inscription registerStudentToUE(Student student , int year , UE ue){
        Inscription newInscription = new Inscription(student,year,ue);
        inscriptionRepository.save(newInscription);
        student.getInscriptions().add(newInscription);
        studentRepository.save(student);
        return newInscription ;
    }

  public  List<Inscription> getInscriptionsOfStudent(Student student){
        return student.getInscriptions();
    }

    public  void unsubscribeStudentFromInscription(Student student , Inscription inscription){
        student.getInscriptions().remove(inscription);
        studentRepository.save(student);
        inscriptionRepository.delete(inscription);
    }
}
