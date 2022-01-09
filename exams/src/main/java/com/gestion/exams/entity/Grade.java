package com.gestion.exams.entity;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Grade {

	@EmbeddedId
	private GradePK gradePK;

	@Basic(optional = false)
	private double value;

	public Grade() {
		super();
	}

	public Grade(Student student, Exam exam, double value) {
		super();
		this.gradePK = new GradePK(exam, student);
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public GradePK getGradePK() {
		return gradePK;
	}



}
