package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.UERepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
}
