package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.StudentDTO;
import com.gestion.exams.entity.Student;
import org.modelmapper.ModelMapper;

public class StudentMapper {

	private static final ModelMapper modelMapper = new ModelMapper() ;

	private StudentMapper(){}

	public static StudentDTO studentToStudentDTO(Student student){
		return  modelMapper.map(student, StudentDTO.class);
	}

}
