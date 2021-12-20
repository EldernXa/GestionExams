package com.gestion.exams.controller;

import com.gestion.exams.DTO.GradeDTO;
import com.gestion.exams.DTO.StudentDTO;
import com.gestion.exams.DTO.mapper.GradeMapper;
import com.gestion.exams.DTO.mapper.StudentMapper;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.GradeService;
import com.gestion.exams.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    GradeService gradeService;
    @Autowired
    StudentService studentService;
    @Autowired
    ExamService examService;

    @GetMapping(path="/exam{id}")
    public List<StudentDTO> getStudentsAndGradesByExam(@PathVariable("id") long idExam){
        List<StudentDTO> studentsDTO = new ArrayList<>();
        List<Student> students = studentService.getStudentsByExamId(idExam);
        for(Student s : students) {
            System.out.println("getting");
            studentsDTO.add(StudentMapper.studentToStudentDTO(s, idExam));
        }
        return studentsDTO;
    }

//    @PostMapping(path="/exam{id}")
//    public void addGrades(@PathVariable("id") long idExam, @RequestBody List<Grade> grades){
//        for(Grade g : grades)
//            gradeService.createGrade(g);
//    }

//    @PostMapping(path="/exam{id}")
//    public void addGrade(@PathVariable("id") long idExam, @RequestBody long idStudent, @RequestBody double gradeValue ){
//            gradeService.createGrade(idExam, idStudent, gradeValue);
//    }
    @PostMapping(path="/exam{id}")
    public Grade addGrade(@PathVariable("id") long idExam, @RequestBody Grade g ){
        System.out.println("controller");
        gradeService.createGrade(g);
        return g;
        //gradeService.createGrade(GradeMapper.gradeDTOToGrade(g));
    }

    @DeleteMapping(path="/exam{id}")
    public void deleteGrade(@PathVariable("id") long idExam, @RequestBody Grade g){
        gradeService.deleteGrade(g);
    }


}
