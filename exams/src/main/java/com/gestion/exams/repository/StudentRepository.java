package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

   Student getStudentByEmail(String email);

}
