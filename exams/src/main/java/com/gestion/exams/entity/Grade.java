package com.gestion.exams.entity;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Grade {

	@EmbeddedId
	private GradePK gradePK;

	@Basic(optional = false)
	private int value;

	public Grade() {
		super();
	}

	public Grade(Student student, Exam exam, int value) {
		super();
		this.gradePK = new GradePK(exam, student);
		this.value = value;
	}



}
