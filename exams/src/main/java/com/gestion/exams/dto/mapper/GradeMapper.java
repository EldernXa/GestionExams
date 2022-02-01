package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.entity.Grade;
import org.modelmapper.ModelMapper;

public class GradeMapper {

	private static final ModelMapper modelMapper = new ModelMapper() ;

	public static Grade gradeDTOToGrade(GradeDTO gradeDTO) {
		Grade g = modelMapper.map(gradeDTO, Grade.class);
		System.out.println("mapper :" + g.toString());
		return g;
	}
}
