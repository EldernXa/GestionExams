package com.gestion.exams.repository;

import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Exam;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{

    Exam getExamByUe(UE ue);

    /*
    List<Exam> findAllByUeAAndYear(UE ue, int year);
    List<Exam> getExamsByUeAndYear(UE ue, int year);
    List<Exam> findExamsByUeAndYear(UE ue, int year);
    List<Exam> getExamByUeAndYear(UE ue, int year);
     */

    @Query("SELECT e FROM Exam e WHERE ( e.ue = :searchValueUe AND  e.year = :searchValueYear )")
    List<Exam> searchExamsByUeAndYear(@Param("searchValueUe") UE ue, @Param("searchValueYear") int year);

    @Query("SELECT s FROM Student s, Exam e, Inscription i WHERE ( e.idExam = :searchValueExam AND  e.year = i.inscriptionPK.year AND e.ue.name = i.inscriptionPK.ue.name AND i.inscriptionPK.student.idStudent = s.idStudent)")
    List<Student> getStudents(@Param("searchValueExam") long idExam);

    void deleteByUe(String name);


}
