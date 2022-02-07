package com.gestion.exams.dto;

public class StudentDTO {

	long idStudent;
	String firstName;
	String lastName;
	String email;
	//List<GradeDTO> grades;
	//GradeDTO grade;
	double grade;

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
	public void setGrade(double grade) {
		this.grade = grade;
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

	public double getGrade() {
		return grade;
	}

}
