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

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    @InjectMocks
    GradeService gradeService;
    @Mock
    private GradeRepository gradeRepository;

    @BeforeAll
    public void initGradeForTest(){
        Student student = new Student("Georges", "Bardaghji", "georgebardagji@gmail.com");
        Exam exam = new Exam();
        int value  = 20;
        Grade grade = new Grade(student, exam, value);
    }

    @org.junit.Test
    public void CreateGradeTest(){
        Student student = new Student("Georges", "Bardaghji", "georgebardagji@gmail.com");
        Exam exam = new Exam();
        int value  = 20;
        Grade grade = new Grade(student, exam, value);
        when(gradeRepository.save(ArgumentMatchers.any(Grade.class))).thenReturn(grade);
        Grade createdGrade = gradeService.createGrade(grade);
        assertEquals(createdGrade.getGradePK(), grade.getGradePK());
        verify(gradeRepository).save(grade);
    }
}
