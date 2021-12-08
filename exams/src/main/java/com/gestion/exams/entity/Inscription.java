package com.gestion.exams.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inscription implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private long idStudent;

	@ManyToOne
	@JoinColumn
	private Student student;

	@Basic(optional = false)
	private int year;

	@ManyToOne
	@JoinColumn
	private UE ue;

	public Inscription() {
		super();
	}

	public Inscription(Student student, int year, UE ue) {
		super();
		this.idStudent = student.getIdStudent();
		this.student = student;
		this.year = year;
		this.ue = ue;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
