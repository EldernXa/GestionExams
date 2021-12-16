package com.gestion.exams.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam {

	@Basic()
	private Date beginDateExam;

	@Basic()
	private Date endDateExam;

	@OneToMany(mappedBy = "gradePK.exam")
	@JsonIgnore
	private List<Grade> grades = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idExam;

	@ManyToOne
	@JoinColumn
	private Period period;

	@ManyToOne
	@JoinColumn
	private Room room;

	@Basic(optional = false)
	private int session;

	@ElementCollection
	private List<String> supervisors = new ArrayList<>();

	@ManyToOne
	@JoinColumn
	private UE ue;

	@Basic(optional = false)
	private int year;

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

	public void addSupervisor(String supervisor) {
		this.supervisors.add(supervisor);
	}

	public Date getBeginDateExam() {
		return beginDateExam;
	}

	public Date getEndDateExam() {
		return endDateExam;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public long getIdExam() {
		return idExam;
	}

	public Period getPeriod() {
		return period;
	}

	public Room getRoom() {
		return room;
	}

	public int getSession() {
		return session;
	}

	public List<String> getSupervisors() {
		return supervisors;
	}

	public UE getUe() {
		return ue;
	}

	public int getYear() {
		return year;
	}

	public void setBeginDateExam(Date beginDateExam) {
		this.beginDateExam = beginDateExam;
	}

	public void setEndDateExam(Date endDateExam) {
		this.endDateExam = endDateExam;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	}

	public void setYear(int year) {
		this.year = year;
	}

}

















