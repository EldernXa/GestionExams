package com.gestion.exams.jpa;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ExamRepository examRepository;

    @Test
    public void getStudentByEmail(){
        Student George = new Student("Georges", "BARDAGHJI", "george@gmail.com");
        studentRepository.save(George);

        Student expectedGeorges = studentRepository.getStudentByEmail(George.getEmail());
        assertEquals(expectedGeorges.getEmail(), George.getEmail());
    }

    @Test
    public void saveStudentTest(){
        Student George = new Student("Georges", "BARDAGHJI", "george@gmail.com");
        studentRepository.save(George);

        Student expectedCreated = studentRepository.getStudentByEmail(George.getEmail());
        assertThat(expectedCreated).isNotNull();
    }

    @Test
    public void deleteStudentTest(){
        Student George = new Student("Georges", "BARDAGHJI", "george@gmail.com");
        studentRepository.save(George);

        assertThat(George).isNotNull();

        studentRepository.delete(George);

        List<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(0);
    }
}
