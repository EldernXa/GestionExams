package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.GradePK;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradePK>{

}
