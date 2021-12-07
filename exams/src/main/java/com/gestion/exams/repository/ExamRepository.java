package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{

}
