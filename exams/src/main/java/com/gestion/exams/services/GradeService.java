package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Grade createGrade(Student student, Exam exam, double gradeValue){
        if(gradeValue<0)
            gradeValue=0;
        else if(gradeValue>20)
            gradeValue=20;
        Grade grade = new Grade(student, exam, gradeValue);
        System.out.println(grade + " -> created with value : " + grade.getValue() + " id student : " + student.getIdStudent() + " id exam : " + exam.getIdExam());
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Grade g1, Grade g2){
        g1.setValue(g2.getValue());
        return gradeRepository.save(g1);
    }

    public void deleteGrade(Grade g){
        //Grade g = gradeRepository.getGradeByStudentAndExam(s.getIdStudent(),e.getIdExam()).get();
        gradeRepository.delete(g);
    }

    public void createAllGradesByExamIfNotExists(long idExam){
        List<Student> students = studentRepository.findStudentByExamId(idExam);
        Optional<Exam> exam = examRepository.findById(idExam);
        for(Student s : students) {
            createGradeByStudentAndExamIfNotExists(s, exam.get());
            System.out.println(s.hasGradeForExam(exam.get()));
        }
    }

    public void createGradeByStudentAndExamIfNotExists(Student student, Exam exam){
        if(!student.hasGradeForExam(exam)) {
            System.out.println("has no grade for exam !!!!!!!");
            createGrade(student, exam, 0);
        }
    }

    public Optional<Grade> getGradeByStudentAndExam(long idStudent, long idExam){
        return gradeRepository.getGradeByStudentAndExam(idStudent,idExam);
    }

    public List<Grade> getGradesByExam(long idExam){
        List<Grade> grades = gradeRepository.searchGradeByExam(idExam);
        return grades;
    }

    public List<Grade> getGradesByStudent(long idStudent){
        List<Grade> grades = gradeRepository.searchGradesByStudent(idStudent);
        System.out.println("nombre notes dans grade service"+grades.size());
        return grades;
    }

    public List<Grade> getAllGradesByExam(long idExam){
        return gradeRepository.searchGradeByExam(idExam);
    }

    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }

}
