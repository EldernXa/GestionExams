package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gestion.exams.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT i.inscriptionPK.student FROM Inscription i, Exam e WHERE ( i.inscriptionPK.ue = e.ue AND e.idExam = :idExam AND i.inscriptionPK.year = e.year) ")
    List<Student> findStudentByExamId(@PathVariable("idExam") long idExam);
   
    Student getStudentByEmail(String email);


}
