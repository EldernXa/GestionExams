package com.gestion.exams.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	private List<Grade> grades = new ArrayList<>();

	@OneToMany(mappedBy = "student")
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


}
