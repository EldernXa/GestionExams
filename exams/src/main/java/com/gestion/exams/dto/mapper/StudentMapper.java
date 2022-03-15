package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.StudentDTO;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import org.modelmapper.ModelMapper;

public class StudentMapper {

	private static final ModelMapper modelMapper = new ModelMapper() ;

	public static StudentDTO studentToStudentDTO(Student student){

		StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
		return studentDTO;
	}

}
