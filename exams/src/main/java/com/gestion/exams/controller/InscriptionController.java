package com.gestion.exams.controller;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.services.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscription")
public class InscriptionController {

    InscriptionService inscriptionService ;

@GetMapping("/all")
public List<Inscription> showInscriptionsOfStudent(Student student){
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
