package com.gestion.exams.services;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.InscriptionPK;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.InscriptionRepository;
import com.gestion.exams.repository.StudentRepository;
import com.gestion.exams.repository.UERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService {

    @Autowired
    InscriptionRepository inscriptionRepository;
    StudentRepository studentRepository;
    UERepository ueRepository;

    public Inscription registerStudentToUE(long id, int year, String nameUE) {
        Student student = studentRepository.getById(id);
        Inscription newInscription = new Inscription(student, year, ueRepository.getUEByName(nameUE));
        inscriptionRepository.save(newInscription);
        student.getInscriptions().add(newInscription);
        studentRepository.save(student);
        return newInscription;
    }

    public List<Inscription> getInscriptionsOfStudent(Student student) {
        return student.getInscriptions();
    }

  /* public void unsubscribeStudentFromInscription(Inscription inscription) {
        Student student = inscription.getStudent();
        student.getInscriptions().remove(inscription);
        studentRepository.save(student);
        inscriptionRepository.delete(inscription);

    }
     public Inscription getInscriptionByStudentAndUeAndYear(Student student,UE ue,int year){
        return inscriptionRepository.getInscriptionByStudentAndUeAndYear(student,ue,year);
     }*/


}
