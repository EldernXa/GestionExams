package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UE {

	@Basic(optional = false)
	private int credit;

	@Column(nullable = false)
	private Discipline discipline;

	@Basic(optional = false)
	private int durationExam;


	@OneToMany(mappedBy = "inscriptionPK.ue")
	@Column
	@JsonIgnore
	private List<Inscription> inscriptions = new ArrayList<>();



	@OneToMany(mappedBy = "ue")
	@JsonIgnore
	private List<Exam> exams = new ArrayList<>();


	@Id
	private String name;

	public UE() {
		super();
	}

	public UE(String name, int credit, int durationExam, Discipline discipline) {
		super();
		this.name = name;
		this.credit = credit;
		this.durationExam = durationExam;
		this.discipline = discipline;
	}

	public int getCredit() {
		return credit;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public int getDurationExam() {
		return durationExam;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public String getName() {
		return name;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public void setDurationExam(int durationExam) {
		this.durationExam = durationExam;
	}

	public void setName(String name) {
		this.name = name;
	}



}
