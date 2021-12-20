package com.gestion.exams.DTO.mapper;

import com.gestion.exams.DTO.GradeDTO;
import com.gestion.exams.DTO.StudentDTO;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    private static final ModelMapper modelMapper = new ModelMapper() ;

    public static StudentDTO studentToStudentDTO(Student student, long idExam){
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        List<GradeDTO> grades = new ArrayList<>();
        for(Grade g : student.getGrades()){
            //System.out.println("database:"+g.toString());
            //GradeDTO gradeDTO = modelMapper.map(g, GradeDTO.class);
            //grades.add(gradeDTO);
            if(g.getGradePK().getExam().getIdExam() == idExam){
                System.out.println("database:"+g.toString());
                GradeDTO gradeDTO = modelMapper.map(g, GradeDTO.class);
                studentDTO.setGrade(gradeDTO);
            }
        }
        //studentDTO.setGrades(grades);
        return studentDTO;
    }

}
