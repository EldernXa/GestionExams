package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.StudentRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(MockitoJUnitRunner.Silent.class)
public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    Student student = new Student("Georges", "Bardaghji", "george@gmail.com");

    @org.junit.Test
    @Transactional
    public void getStudentsByExamIdTest(){
        Exam exam  = new Exam();
        List<Student> studentsByExam = new ArrayList<>();
        when(studentRepository.findStudentByExamId(exam.getIdExam())).thenReturn(studentsByExam);

        List<Student> expectedStudentsByExam = studentService.getStudentsByExamId(exam.getIdExam());
        assertEquals(expectedStudentsByExam, studentsByExam);
        verify(studentRepository).findStudentByExamId(exam.getIdExam());
    }

    @org.junit.Test
    @Transactional
    public void getStudentByIdTest(){
        Optional<Student> student1;
       when(studentRepository.findById(student.getIdStudent())).thenReturn(Optional.of(student));
       Optional<Student> expectedStudent  = studentService.getStudentById(student.getIdStudent());

       assertEquals(expectedStudent, Optional.of(student));
       verify(studentRepository).findById(student.getIdStudent());
    }

    @org.junit.Test
    @Transactional
    public void getStudentByEmailTest(){
        when(studentRepository.getStudentByEmail(student.getEmail())).thenReturn(student);
        Student expectedStudent = studentService.getStudentByEmail(student.getEmail());

        assertEquals(expectedStudent, student);
        verify(studentRepository).getStudentByEmail(student.getEmail());
    }
}
