package com.gestion.exams.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Authentification {

	@Id
	private String email;

	@Basic(optional = false)
	private String password;


	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles= new ArrayList<>();

	public Authentification() {
		super();
	}

	public Authentification(String email, String password, Collection<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
