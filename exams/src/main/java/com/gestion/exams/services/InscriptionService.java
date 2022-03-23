package com.gestion.exams.services;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
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
    @Autowired
    StudentRepository studentRepository;
    @Autowired
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

  public void unsubscribeStudentFromInscription(Inscription inscription) {
        inscriptionRepository.delete(inscription);

    }

}
