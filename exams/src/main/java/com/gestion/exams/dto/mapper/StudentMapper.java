package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.StudentDTO;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import org.modelmapper.ModelMapper;

public class StudentMapper {

	private static final ModelMapper modelMapper = new ModelMapper() ;

	public static StudentDTO studentToStudentDTO(Student student, long idExam){

		StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
		if(student.getGrades().size() == 0)
			studentDTO.setGrade(-1);
		for(Grade g : student.getGrades())
			if(g.getGradePK().getExam().getIdExam() == idExam)
				studentDTO.setGrade(g.getValue());

		return studentDTO;
	}

}
