package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@OneToMany(mappedBy = "gradePK.student", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Grade> grades = new ArrayList<>();

	@OneToMany(mappedBy = "inscriptionPK.student")
	@JsonIgnore
	private List<Inscription> inscriptions = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String email,String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password=password;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
