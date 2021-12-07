package com.gestion.exams.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class GradePK implements Serializable{

	// TODO do compareTo
	private static final long serialVersionUID = 1L;

	private Exam exam;
	private Student student;

	public GradePK() {
		super();
	}

	public GradePK(Exam exam, Student student) {
		super();
		this.exam = exam;
		this.student = student;
	}



}
