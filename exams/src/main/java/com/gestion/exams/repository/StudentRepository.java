package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT i.inscriptionPK.student FROM Inscription i, Exam e WHERE ( i.inscriptionPK.ue = e.ue AND e.idExam = :searchValue ) ")
    List<Student> findStudentByExamId(@Param("searchValue") long idExam);

}
