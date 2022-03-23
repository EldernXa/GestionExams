package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Student;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Student>{

}
