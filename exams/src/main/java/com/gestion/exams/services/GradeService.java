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

    public Grade createGrade(Grade grade){
        if(grade.getValue()<0)
            grade.setValue(0);
        else if(grade.getValue()>20)
            grade.setValue(20);
        System.out.println(grade + " : new value : " + grade.getValue());
        return gradeRepository.save(grade);
    }

    public Grade createGrade(long idExam, long idStudent, double gradeValue){
        Optional<Student> student = studentRepository.findById(idStudent);
        Optional<Exam> exam = examRepository.findById(idExam);
        if(student.isPresent() && exam.isPresent())
            return gradeRepository.save(new Grade(student.get(), exam.get(), gradeValue));
        return null;
    }

    public Grade updateGrade(Grade g1, Grade g2){
        g1.setValue(g2.getValue());
        return gradeRepository.save(g1);
    }

    public void deleteGrade(Grade g){
        //Grade g = gradeRepository.getGradeByStudentAndExam(s.getIdStudent(),e.getIdExam()).get();
        gradeRepository.delete(g);
    }

    public Optional<Grade> getGradeByStudentAndExam(long idStudent, long idExam){
        return gradeRepository.getGradeByStudentAndExam(idStudent,idExam);
    }

    public List<Grade> getAllGradesByExam(long idExam){
        return gradeRepository.searchGradeByExam(idExam);
    }

}
