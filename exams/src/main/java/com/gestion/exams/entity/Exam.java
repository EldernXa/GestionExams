package com.gestion.exams.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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

	@ElementCollection
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
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UE ue;

	@OneToMany(mappedBy = "gradePK.exam", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Grade> grades = new ArrayList<>();

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

	public Date getBeginDateExam() {
		return beginDateExam;
	}

	public void setBeginDateExam(Date beginDateExam) {
		this.beginDateExam = beginDateExam;
	}

	public Date getEndDateExam() {
		return endDateExam;
	}

	public void setEndDateExam(Date endDateExam) {
		this.endDateExam = endDateExam;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	}

	public long getIdExam() {
		return idExam;
	}

	public List<String> getSupervisors() {
		return supervisors;
	}

	public void addSupervisor(String supervisor) {
		this.supervisors.add(supervisor);
	}

	public List<Grade> getGrades() {
		return grades;
	}

}

















