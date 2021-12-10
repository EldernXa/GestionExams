package com.gestion.exams.controller;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    GradeService gradeService;

//    @GetMapping(path="/exam{id}")
//    public List<Grade> getAllGradesByExam(@PathVariable("id") int idExam){
//        List<Grade> grades = gradeService.getAllGradesByExam(idExam);
//        return grades;
//    }

    @GetMapping(path="/exam{id}")
    public List<Grade> getAllGradesByExam(@PathVariable("id") long idExam){
        List<Grade> grades = gradeService.getAllGradesByExam(idExam);
        return grades;
    }

    @PostMapping(path="/exam{id}")
    public void addGrades(@PathVariable("id") int idExam, @RequestBody List<Grade> grades){
        for(Grade g : grades)
            gradeService.addGrade(g);
    }

}
