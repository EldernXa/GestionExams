package com.gestion.exams.dto;

import com.gestion.exams.entity.Discipline;

public class UeDTO {

	String name;
	int credit;
	int durationExam;
	Discipline discipline;

	public UeDTO() {
	}

	public UeDTO(String name, int credit, int durationExam, Discipline discipline) {
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
}
