package com.gestion.exams.DTO.mapper;

import com.gestion.exams.DTO.GradeDTO;
import com.gestion.exams.DTO.StudentDTO;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class GradeMapper {

    private static final ModelMapper modelMapper = new ModelMapper() ;

    public static Grade gradeDTOToGrade(GradeDTO gradeDTO) {
        Grade g = modelMapper.map(gradeDTO, Grade.class);
        System.out.println("mapper :" + g.toString());
        return g;
    }
}
