package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.UERepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@RunWith(MockitoJUnitRunner.Silent.class)
public class GradeServiceTest {

    @InjectMocks
    GradeService gradeService;
    @Mock
    private GradeRepository gradeRepository;

    Student student = new Student("Georges", "Bardaghji", "georgebardagji@gmail.com");
    Exam exam = new Exam();
    double value  = 20;
    Grade grade = new Grade(student, exam, value);

    @org.junit.Test
    public void CreateGradeTest(){
        when(gradeRepository.save(ArgumentMatchers.any(Grade.class))).thenReturn(grade);
        Grade createdGrade = gradeService.createGrade(grade);

        assertEquals(createdGrade.getGradePK(), grade.getGradePK());
        verify(gradeRepository).save(grade);
    }

    @org.junit.Test
    @Transactional
    public void updateGradeTest(){
        Grade updatedGrade = new Grade(student, exam, value);
        updatedGrade.setValue(10);

        given(gradeRepository.findById(grade.getGradePK())).willReturn(Optional.of(grade));
        gradeService.updateGrade(grade, updatedGrade);

        verify(gradeRepository).save(grade);
    }

    @org.junit.Test
    @Transactional
    public void deleteGradeTest(){
        when(gradeRepository.findById(grade.getGradePK())).thenReturn(Optional.of(grade));
        gradeService.deleteGrade(grade);

        verify(gradeRepository).delete(grade);
    }

    @org.junit.Test
    @Transactional
    public void getGradeByStudentAndExam(){
        when(gradeRepository.getGradeByStudentAndExam(student.getIdStudent(), exam.getIdExam())).thenReturn(Optional.of(grade));
        Optional<Grade> studentGrade = gradeService.getGradeByStudentAndExam(student.getIdStudent(), exam.getIdExam());

        assertEquals(studentGrade.get().getGradePK(), grade.getGradePK());
        verify(gradeRepository).getGradeByStudentAndExam(student.getIdStudent(), exam.getIdExam());
    }

    @org.junit.Test
    @Transactional
    public void getAllGradesByExamTest(){
        List<Grade> grades  = new ArrayList<>();
        when(gradeRepository.searchGradeByExam(exam.getIdExam())).thenReturn(grades);
        List<Grade> searchedGrades =  gradeService.getAllGradesByExam(exam.getIdExam());

        assertEquals(searchedGrades, grades);
        verify(gradeRepository).searchGradeByExam(exam.getIdExam());
    }

    @org.junit.Test
    @Transactional
    public void getAllGradesTest(){
        List<Grade> allGrades = new ArrayList<>();
        when(gradeRepository.findAll()).thenReturn(allGrades);
        List<Grade> expectedAllGrades = gradeService.getAllGrades();

        assertEquals(expectedAllGrades, allGrades);
        verify(gradeRepository).findAll();
    }

}
