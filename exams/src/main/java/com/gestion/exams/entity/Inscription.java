package com.gestion.exams.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Inscription implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InscriptionPK inscriptionPK;

	/*
	@Basic(optional = false)
	private int year;
	*/

	public Inscription() {
		super();
	}

	public Inscription(Student student, int year, UE ue) {
		super();
		this.inscriptionPK = new InscriptionPK(ue, student, year);
		//this.inscriptionPK = new InscriptionPK(ue, student);
		//this.year = year;
	}

	public Student getStudent() {
		return this.inscriptionPK.getStudent();
	}

	public void setStudent(Student student) {
		this.inscriptionPK.setStudent(student);
	}

	public int getYear() {
		//return year;
		return this.inscriptionPK.getYear();
	}

	public void setYear(int year) {
		//this.year = year;
		this.inscriptionPK.setYear(year);
	}

	public UE getUe() {
		return this.inscriptionPK.getUe();
	}

	public void setUe(UE ue) {
		this.inscriptionPK.setUe(ue);
	}

	public InscriptionPK getInscriptionPK() {
		return inscriptionPK;
	}

}
