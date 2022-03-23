package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.GradePK;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradePK>{

    @Query("SELECT g FROM Grade g WHERE ( g.gradePK.exam.idExam = :searchValue ) ")
    List<Grade> searchGradeByExam(@Param("searchValue") long idExam);

    @Query("SELECT g FROM Grade g WHERE ( g.gradePK.student.idStudent = :searchValueStudent AND g.gradePK.exam.idExam = :searchValueExam ) ")
    Optional<Grade> getGradeByStudentAndExam(@Param("searchValueStudent") long idStudent, @Param("searchValueExam") long idExam);

    @Query("SELECT g FROM Grade g WHERE ( g.gradePK.student.idStudent = :searchValueStudent ) ")
    List<Grade> searchGradesByStudent(@Param("searchValueStudent") long idStudent);

    @Query("SELECT g FROM Grade g WHERE ( g.gradePK.student.idStudent = :idStudent AND g.gradePK.exam.ue.name = :ueName AND g.value >= 10 ) ")
    List<Grade> getGradesMoreThan10ByStudentAndUE(@PathVariable("idStudent") long idStudent, @PathVariable("ue_name") String ueName);

}
