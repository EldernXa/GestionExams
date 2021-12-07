package com.gestion.exams.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room {

	@Id
	private String name;

	@Basic(optional = false)
	private int capacity;

	@OneToMany(mappedBy = "room")
	private List<Exam> exams = new ArrayList<>();

	public Room() {
		super();
	}

	public Room(String name, int capacity) {
		super();
		this.name = name;
		this.capacity = capacity;
	}



}
