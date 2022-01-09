package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idStudent;

	@Basic(optional = false)
	private String firstName;

	@Basic(optional = false)
	private String lastName;

	@Basic(optional = false)
	private String email;

	// TODO verify mappedBy.
	@OneToMany(mappedBy = "gradePK.student", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Grade> grades = new ArrayList<>();

	@OneToMany(mappedBy = "inscriptionPK.student")
	@JsonIgnore
	private List<Inscription> inscriptions = new ArrayList<>();

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getIdStudent() {
		return idStudent;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean hasGradeForExam(Exam exam){
		for(Grade g: grades)
			if(g.getGradePK().getExam().getIdExam() == exam.getIdExam())
				return true;
		return false;
	}

}
