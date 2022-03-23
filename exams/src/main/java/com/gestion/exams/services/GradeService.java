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
        return gradeRepository.save(grade);
    }

    public Grade createGrade(Student student, Exam exam, double gradeValue){
        if(gradeValue<0)
            gradeValue=0;
        else if(gradeValue>20)
            gradeValue=20;
        Grade grade = new Grade(student, exam, gradeValue);
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Grade g1, Grade g2){
        g1.setValue(g2.getValue());
        return gradeRepository.save(g1);
    }

    public void deleteGrade(Grade g){
        gradeRepository.delete(g);
    }

    public void createAllGradesByExamIfNotExists(long idExam){
        List<Student> students = studentRepository.findStudentByExamId(idExam);
        Optional<Exam> exam = examRepository.findById(idExam);
        for(Student s : students) {
            boolean hasMoreThan10 = false;
            if(exam.isPresent() && exam.get().getSession() == 2){
                List<Grade> grades = s.getGrades();
                for(Grade g : grades)
                    if(g.getGradePK().getExam().getUe().getName().equals(exam.get().getUe().getName())
                        && g.getGradePK().getExam().getYear() == exam.get().getYear()
                        && g.getGradePK().getExam().getSession() == 1
                        && g.getValue() >= 10)
                        hasMoreThan10 = true;

            }
            if(!hasMoreThan10 && exam.isPresent())
                createGradeByStudentAndExamIfNotExists(s, exam.get());
        }
    }

    public void createGradeByStudentAndExamIfNotExists(Student student, Exam exam){
        if(!student.hasGradeForExam(exam)) {
            createGrade(student, exam, -1);
        }
    }

    public Optional<Grade> getGradeByStudentAndExam(long idStudent, long idExam){
        return gradeRepository.getGradeByStudentAndExam(idStudent,idExam);
    }

    public List<Grade> getGradesByExam(long idExam){
        return gradeRepository.searchGradeByExam(idExam);
    }

    public List<Grade> getGradesByStudent(long idStudent){
        return gradeRepository.searchGradesByStudent(idStudent);
    }

    public List<Grade> getAllGradesByExam(long idExam){
        return gradeRepository.searchGradeByExam(idExam);
    }

    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }

    public List<Grade> getGradesMoreThan10ByStudentAndUE(long idStudent, String ueName){
        return gradeRepository.getGradesMoreThan10ByStudentAndUE(idStudent,ueName);
    }

}
