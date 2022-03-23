package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Room  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	private int capacity;

	@OneToMany(mappedBy = "room")
	@Column
	@JsonIgnore
	private List<Exam> exams = new ArrayList<>();

	@Id
	private String name;

	public Room() {
		super();
	}

	public Room(String name, int capacity) {
		super();
		this.name = name;
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public String getName() {
		return name;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setName(String name) {
		this.name = name;
	}



}
