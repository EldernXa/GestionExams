package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

import javax.persistence.*;

@Entity
public class Student {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idStudent;

	@Basic(optional = false)
	private String firstName;

	@Basic(optional = false)
	private String lastName;

	@Basic(optional = false)
	private String email;

	private String password;
	private String roles;
	private String authorities;
	private boolean isActive;
	private boolean isNotLocked;


	@OneToMany(mappedBy = "gradePK.student", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Grade> grades = new ArrayList<>();

	@OneToMany(mappedBy = "inscriptionPK.student")
	@JsonIgnore
	private List<Inscription> inscriptions = new ArrayList<>();


	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String email,String password, String roles, String authorities, boolean isActive, boolean isNotLocked) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password=password;
		this.roles = roles;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
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
		for(Grade g: grades) {
			if(g.getGradePK().getExam().getIdExam() == exam.getIdExam()) {
				return true;
			}
		}
		return false;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public void setNotLocked(boolean notLocked) {
		isNotLocked = notLocked;
	}

	public String getRoles() {
		return roles;
	}

	public String getAuthorities() {
		return authorities;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
