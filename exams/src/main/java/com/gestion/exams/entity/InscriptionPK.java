package com.gestion.exams.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class InscriptionPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UE ue;

	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn
	private Student student;

	@Basic(optional = false)
	private int year;

	public InscriptionPK() {
		super();
	}

	public InscriptionPK(UE ue, Student student) {
		super();
		this.ue = ue;
		this.student = student;
	}

	public InscriptionPK(UE ue, Student student, int year) {
		super();
		this.ue = ue;
		this.student = student;
		this.year = year;
	}



	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
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
