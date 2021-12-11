package com.gestion.exams.controller;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.services.GradeService;
import com.gestion.exams.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    GradeService gradeService;
    @Autowired
    StudentService studentService;

    @GetMapping(path="/exam{id}")
    public ResponseEntity<HashMap<Student,Grade>> getStudentsAndGradesByExam(@PathVariable("id") long idExam){
        HashMap<Student,Grade> hash = new HashMap<>();
        List<Student> students = studentService.getStudentsByExamId(idExam);
        for(Student s : students)
            hash.put(s, gradeService.getGradeByStudentAndExam(s.getIdStudent(), idExam).get() );
        return new ResponseEntity<>(hash, HttpStatus.OK);
    }

    @PostMapping(path="/exam{id}")
    public void addGrades(@PathVariable("id") long idExam, @RequestBody List<Grade> grades){
        for(Grade g : grades)
            gradeService.addGrade(g);
    }

//    @PostMapping(path="/exam{id}")
//    public void addGrade(@PathVariable("id") long idExam, @RequestBody long idStudent, @RequestBody int gradeValue ){
//            gradeService.addGrade(idExam, idStudent, gradeValue);
//    }

}
