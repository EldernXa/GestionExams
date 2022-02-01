package com.gestion.exams.controller;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.StudentRepository;
import com.gestion.exams.services.InscriptionService;
import com.gestion.exams.services.StudentService;
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
    @Autowired
    StudentService studentService ;
    

@GetMapping("/all/{email}")
public List<Inscription> showInscriptionsOfStudent(@PathVariable String email){ //student will be replaced with Principal
    Student student = studentRepository.getStudentByEmail(email);
    System.out.println("access");
    return inscriptionService.getInscriptionsOfStudent(student);
}
@PostMapping("/register/{id}/{year}/{nameUE}")
public Inscription registerStudentToUE(@PathVariable long id  ,@PathVariable int year , @PathVariable String nameUE,UE ue){
    return inscriptionService.registerStudentToUE(id,year,nameUE);
}

@DeleteMapping("/unsubscribe/{id}/{year}/{nameUE}")
public void unsubscribeStudentFromInscription(@PathVariable long id ,@PathVariable int year , @PathVariable String nameUE){
    inscriptionService.unsubscribeStudentFromInscription(id, year, nameUE);
}





}
