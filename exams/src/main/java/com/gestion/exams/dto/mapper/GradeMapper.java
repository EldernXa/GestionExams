package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.GradeService;
import com.gestion.exams.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class GradeMapper {

	private static final ModelMapper modelMapper = new ModelMapper() ;


	public static Grade gradeDTOToGrade(GradeDTO gradeDTO, GradeService gradeService) {
		/*
		Grade g = modelMapper.map(gradeDTO, Grade.class);
		System.out.println("mapper :" + g.toString());
		return g;
		 */
		return gradeService.getGradeByStudentAndExam(gradeDTO.getIdStudent(),gradeDTO.getIdExam()).get();
	}

	public static GradeDTO gradeToGradeDTO(Grade grade){
		GradeDTO gradeDTO = modelMapper.map(grade, GradeDTO.class);
		gradeDTO.setIdExam(grade.getGradePK().getExam().getIdExam());
		gradeDTO.setSession(grade.getGradePK().getExam().getSession());
		gradeDTO.setUe_name(grade.getGradePK().getExam().getUe().getName());
		gradeDTO.setYear(grade.getGradePK().getExam().getYear());
		gradeDTO.setIdStudent(grade.getGradePK().getStudent().getIdStudent());
		gradeDTO.setFirstName(grade.getGradePK().getStudent().getFirstName());
		gradeDTO.setLastName(grade.getGradePK().getStudent().getLastName());
		return gradeDTO;
	}
}
