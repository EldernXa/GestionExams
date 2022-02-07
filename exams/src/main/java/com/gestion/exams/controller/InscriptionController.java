package com.gestion.exams.controller;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.StudentRepository;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.InscriptionService;
import com.gestion.exams.services.StudentService;
import com.gestion.exams.services.UEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inscription")

public class InscriptionController {

    @Autowired
    InscriptionService inscriptionService ;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService ;
    @Autowired
    UEService ueService;
    

@GetMapping("/all")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public List<Inscription> showInscriptionsOfStudent( Principal principal){ //student will be replaced with Principal
    Student student = studentRepository.getStudentByEmail(principal.getName());
    System.out.println("access");
    return inscriptionService.getInscriptionsOfStudent(student);
}

    @GetMapping("/all/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Inscription> showInscriptionsOfStudentByAdmin(@PathVariable String email){
        Student student = studentRepository.getStudentByEmail(email);
        System.out.println("access");
        return inscriptionService.getInscriptionsOfStudent(student);
    }

@PostMapping("/register/{year}/{nameUE}")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public Inscription registerStudentToUE(Principal principal,@PathVariable int year , @PathVariable String nameUE,UE ue){
    Student student = studentService.getStudentByEmail(principal.getName());
    return inscriptionService.registerStudentToUE(student.getIdStudent(),year,nameUE);
}

    @PostMapping("/register/{email}/{year}/{nameUE}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Inscription registerStudentToUeByAdmin(@PathVariable String email,@PathVariable int year , @PathVariable String nameUE){
        Student student = studentService.getStudentByEmail(email);
        return inscriptionService.registerStudentToUE(student.getIdStudent(),year,nameUE);
    }



@DeleteMapping("/unsubscribe/{year}/{nameUE}")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public void unsubscribeStudentFromInscription(Principal principal ,@PathVariable int year , @PathVariable String nameUE){
    Student student = studentService.getStudentByEmail(principal.getName());
    UE ue = ueService.getUeByName(nameUE);
    student.getInscriptions().forEach(inscription -> {
        if (inscription.getYear()==year){
            if (inscription.getInscriptionPK().getUe()==ue)
            inscriptionService.unsubscribeStudentFromInscription(inscription);
        }
    });
    return ;
}



    @DeleteMapping("/unsubscribe/{email}/{year}/{nameUE}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void unsubscribeStudentFromInscriptionByAdmin(@PathVariable String email,@PathVariable int year , @PathVariable String nameUE){
        Student student = studentService.getStudentByEmail(email);
        UE ue = ueService.getUeByName(nameUE);
        student.getInscriptions().forEach(inscription -> {
            if (inscription.getYear()==year){
                if (inscription.getInscriptionPK().getUe()==ue)
                    inscriptionService.unsubscribeStudentFromInscription(inscription);
            }
        });
        return ;
    }


}






