package com.gestion.exams.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UE {

	@Id
	private String name;

	@Basic(optional = false)
	private int credit;

	@Basic(optional = false)
	private int durationExam;

	@OneToMany(mappedBy = "ue")
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



}
