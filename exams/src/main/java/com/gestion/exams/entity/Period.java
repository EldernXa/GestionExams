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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Period {

	@Column(nullable = false)
	private Date beginDatePeriod;

	@Column(nullable = false)
	private Date endDatePeriod;

	@OneToMany(mappedBy = "period")
	@JsonIgnore
	private List<Exam> exams = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Basic(optional = false)
	private String name;

	public Period() {
		super();
	}

	public Period(Date beginDatePeriod, Date endDatePeriod, String name) {
		super();
		this.beginDatePeriod = beginDatePeriod;
		this.endDatePeriod = endDatePeriod;
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBeginDatePeriod() {
		return beginDatePeriod;
	}

	public Date getEndDatePeriod() {
		return endDatePeriod;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setBeginDatePeriod(Date beginDatePeriod) {
		this.beginDatePeriod = beginDatePeriod;
	}

	public void setEndDatePeriod(Date endDatePeriod) {
		this.endDatePeriod = endDatePeriod;
	}

	public void setName(String name) {
		this.name = name;
	}





}