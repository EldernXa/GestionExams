package com.gestion.exams.services;

import com.gestion.exams.configuration.MyUserDetails;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.Style;

public class UserDetailesService implements UserDetailsService {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String studentEmail) throws UsernameNotFoundException {

            Student student = studentRepository.getStudentByEmail(studentEmail);

            if (student == null) {
                throw new UsernameNotFoundException("Could not find user");
            }

            return new MyUserDetails(student);
    }
}
