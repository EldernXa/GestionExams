package com.gestion.exams.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class StudentDTO {

	@NotNull
	long id;

	@NotEmpty
	String firstName;

	@NotEmpty
	String lastName;

	@NotEmpty
	String email;

	//List<GradeDTO> grades;

	GradeDTO grade;

	public void setGrade(GradeDTO grade) {
		this.grade = grade;
	}

}
