package com.gestion.exams.repository;

import com.gestion.exams.entity.InscriptionPK;
import com.gestion.exams.entity.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Student>{
    //Inscription getInscriptionByInscriptionPKAndYear(InscriptionPK inscriptionPK,int year);
  //  Inscription getInscriptionByStudentAndUeAndYear(Student student , UE ue,int year );

}
