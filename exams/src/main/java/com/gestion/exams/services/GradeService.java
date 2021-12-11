package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    StudentRepository studentRepository;

    public Grade addGrade(Grade grade){
        return gradeRepository.save(grade);
    }

    public Grade addGrade(long idExam, long idStudent, int gradeValue){
        Optional<Student> student = studentRepository.findById(idStudent);
        Optional<Exam> exam = examRepository.findById(idExam);
        if(student.isPresent() && exam.isPresent())
            return new Grade(student.get(), exam.get(), gradeValue);
        return null;
    }

    public Optional<Grade> getGradeByStudentAndExam(long idStudent, long idExam){
        return gradeRepository.getGradeByStudentAndExam(idStudent,idExam);
    }

    public List<Grade> getAllGradesByExam(long idExam){
        return gradeRepository.searchGradeByExam(idExam);
    }

}
