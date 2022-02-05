package com.gestion.exams.dto;

import com.gestion.exams.entity.Discipline;

public class UeDTO {

	String name;
	int credit;
	int duratioExam;
	Discipline discipline;

	public UeDTO() {
	}

	public UeDTO(String name, int credit, int duratioExam, Discipline discipline) {
		this.name = name;
		this.credit = credit;
		this.duratioExam = duratioExam;
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

	public int getDuratioExam() {
		return duratioExam;
	}

	public void setDuratioExam(int duratioExam) {
		this.duratioExam = duratioExam;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}


}
