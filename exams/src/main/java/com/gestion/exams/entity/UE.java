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

	@Id
	private String name;

	@Basic(optional = false)
	private int credit;

	@Basic(optional = false)
	private int durationExam;

	@JsonIgnore
	@OneToMany(mappedBy = "inscriptionPK.ue")
	private List<Inscription> inscriptions = new ArrayList<>();

	@OneToMany(mappedBy = "ue")
	private List<Exam> exams = new ArrayList<>();

	@Column(nullable = false)
	private Discipline discipline;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getDurationExam() {
		return durationExam;
	}

	public void setDurationExam(int durationExam) {
		this.durationExam = durationExam;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public List<Exam> getExams() {
		return exams;
	}



}
