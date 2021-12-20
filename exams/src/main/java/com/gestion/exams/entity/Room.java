package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Room {

	@Id
	private String name;

	@Basic(optional = false)
	private int capacity;

	@OneToMany(mappedBy = "room")
	@Column
	@JsonIgnore
	private List<Exam> exams = new ArrayList<>();

	public Room() {
		super();
	}

	public Room(String name, int capacity) {
		super();
		this.name = name;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Exam> getExams() {
		return exams;
	}



}
