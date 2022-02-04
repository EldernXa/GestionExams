package com.gestion.exams.services;

import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudentsByExamId(long idExam){
        return studentRepository.findStudentByExamId(idExam);
    }

    public Optional<Student> getStudentById(long idStudent){
        return studentRepository.findById(idStudent);
    }

    public Student getStudentByEmail(String email){ return studentRepository.getStudentByEmail(email);}
}
