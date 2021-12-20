package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class InscriptionPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn
	private UE ue;

	@ManyToOne
	@JoinColumn
	private Student student;

	public InscriptionPK() {
		super();
	}

	public InscriptionPK(UE ue, Student student) {
		super();
		this.ue = ue;
		this.student = student;
	}

	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
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
