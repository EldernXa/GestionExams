package com.gestion.exams.dto;


import com.gestion.exams.dto.mapper.ExamMapper;
import com.gestion.exams.dto.mapper.StudentMapper;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Student;

public class GradeDTO {

    //private ExamDTO exam;
		private long idExam;
		private int year;
		private int session;
		private String ue_name;
    //private StudentDTO student;
		private long idStudent;
		private String firstName;
		private String lastName;
	private double value;

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	/*
	public void setExam(Exam exam) {
		this.exam = ExamMapper.examToExamDTO(exam);
	}

	public ExamDTO getExam() {
		return exam;
	}

	public void setStudent(Student student) {
		this.student = StudentMapper.studentToStudentDTO(student);
	}

	public StudentDTO getStudent() {
		return student;
	}
	 */

	public void setYear(int year) {
		this.year = year;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}

	public void setIdExam(long idExam) {
		this.idExam = idExam;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public void setUe_name(String ue_name) {
		this.ue_name = ue_name;
	}

	public int getYear() {
		return year;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public long getIdStudent() {
		return idStudent;
	}

	public int getSession() {
		return session;
	}

	public long getIdExam() {
		return idExam;
	}

	public String getUe_name() {
		return ue_name;
	}
}
