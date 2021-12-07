package com.gestion.exams.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idExam;

	@Basic(optional = false)
	private Date beginDateExam;

	@Basic(optional = false)
	private Date endDateExam;

	@Basic(optional = false)
	private int session;

	@Column(nullable = false)
	private List<String> supervisors = new ArrayList<>();

	@Basic(optional = false)
	private int year;

	@ManyToOne
	@JoinColumn
	private Room room;

	@ManyToOne
	@JoinColumn
	private Period period;

	@ManyToOne
	@JoinColumn
	private UE ue;

	public Exam() {
		super();
	}

	public Exam(Date beginDateExam, Date endDateExam, int session, int year, Room room, Period period, UE ue) {
		super();
		this.beginDateExam = beginDateExam;
		this.endDateExam = endDateExam;
		this.session = session;
		this.year = year;
		this.room = room;
		this.period = period;
		this.ue = ue;
	}

}

















