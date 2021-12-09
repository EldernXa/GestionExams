package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.GradePK;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradePK>{

    @Query("SELECT g FROM Grade g WHERE ( g.gradePK.exam.idExam = :searchValue ) ")
    List<Grade> searchGradeByExam(@Param("searchValue") int idExam);

}
