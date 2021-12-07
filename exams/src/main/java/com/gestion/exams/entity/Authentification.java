package com.gestion.exams.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authentification {

	@Id
	private String email;

	@Basic(optional = false)
	private String password;

	@Basic(optional = false)
	private String role;

	public Authentification() {
		super();
	}

	public Authentification(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

}
