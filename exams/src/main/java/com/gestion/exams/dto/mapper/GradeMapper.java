package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.services.GradeService;
import org.modelmapper.ModelMapper;

import java.util.Optional;


public class GradeMapper {

	private static final ModelMapper modelMapper = new ModelMapper() ;

	private GradeMapper(){}

	public static Grade gradeDTOToGrade(GradeDTO gradeDTO, GradeService gradeService) {
		Optional<Grade> g = gradeService.getGradeByStudentAndExam(gradeDTO.getIdStudent(),gradeDTO.getIdExam());
		if( g.isPresent()){
			Grade grade = g.get();
			grade.setValue(gradeDTO.getValue());
			return grade;
		}
		return null;
	}

	public static GradeDTO gradeToGradeDTO(Grade grade){
		GradeDTO gradeDTO = modelMapper.map(grade, GradeDTO.class);
		gradeDTO.setIdExam(grade.getGradePK().getExam().getIdExam());
		gradeDTO.setSession(grade.getGradePK().getExam().getSession());
		gradeDTO.setUeName(grade.getGradePK().getExam().getUe().getName());
		gradeDTO.setCredit(grade.getGradePK().getExam().getUe().getCredit());
		gradeDTO.setYear(grade.getGradePK().getExam().getYear());
		gradeDTO.setIdStudent(grade.getGradePK().getStudent().getIdStudent());
		gradeDTO.setFirstName(grade.getGradePK().getStudent().getFirstName());
		gradeDTO.setLastName(grade.getGradePK().getStudent().getLastName());
		return gradeDTO;
	}
}
