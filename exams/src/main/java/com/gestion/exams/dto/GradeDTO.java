package com.gestion.exams.dto;

public class GradeDTO {

		private long idExam;
		private int year;
		private int session;
		private String ueName;
		private int credit;
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

	public void setUeName(String ueName) {
		this.ueName = ueName;
	}

	public void setCredit(int credit) {
		this.credit = credit;
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

	public String getUeName() {
		return ueName;
	}

	public int getCredit() {
		return credit;
	}
}
