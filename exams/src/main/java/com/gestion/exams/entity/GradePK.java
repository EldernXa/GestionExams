package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class GradePK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Exam exam;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Student student;

	public GradePK() {
		super();
	}

	public GradePK(Exam exam, Student student) {
		super();
		this.exam = exam;
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
