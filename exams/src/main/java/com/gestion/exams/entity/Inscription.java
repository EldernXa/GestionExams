package com.gestion.exams.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inscription {

	@Id
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
		this.student = student;
		this.year = year;
		this.ue = ue;
	}

}
