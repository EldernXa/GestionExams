package com.gestion.exams.dto;

import com.gestion.exams.dto.mapper.GradeMapper;
import com.gestion.exams.entity.Grade;

import java.util.List;

public class StudentDTO {

	long idStudent;
	String firstName;
	String lastName;
	String email;

	List<GradeDTO> grades;

	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGrades(List<Grade> grades) {
		for(Grade g : grades)
			this.grades.add(GradeMapper.gradeToGradeDTO(g));
	}

	public long getIdStudent() {
		return idStudent;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public List<GradeDTO> getGrades() {
		return grades;
	}
}
