package com.gestion.exams.controller;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.StudentRepository;
import com.gestion.exams.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscription")
public class InscriptionController {

    @Autowired
    InscriptionService inscriptionService ;
    @Autowired
    StudentRepository studentRepository;

@GetMapping("/all/{email}")
public List<Inscription> showInscriptionsOfStudent(@PathVariable String email){ //student will be replaced with Principal
    Student student = studentRepository.getStudentByEmail(email);
    System.out.println("access");
    return inscriptionService.getInscriptionsOfStudent(student);
}
@PostMapping("/register")
public Inscription registerStudentToUE(Student student , int year , UE ue){
    return inscriptionService.registerStudentToUE(student,year,ue);
}

@DeleteMapping("/unsubscribe")
public void unsubscribeStudentFromInscription(Student student ,Inscription inscription){
    inscriptionService.unsubscribeStudentFromInscription(student,inscription);
}





}
