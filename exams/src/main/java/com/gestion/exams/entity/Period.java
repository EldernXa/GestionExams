package com.gestion.exams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

@Entity
public class Period {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private Date beginDatePeriod;

	@Column(nullable = false)
	private Date endDatePeriod;

	@Basic(optional = false)
	private String name;

	@OneToMany(mappedBy = "period")
	@JsonIgnore
	private List<Exam> exams = new ArrayList<>();

	public Period() {
		super();
	}

	public Period(Date beginDatePeriod, Date endDatePeriod, String name) {
		super();
		this.beginDatePeriod = beginDatePeriod;
		this.endDatePeriod = endDatePeriod;
		this.name = name;
	}

	public Date getBeginDatePeriod() {
		return beginDatePeriod;
	}

	public void setBeginDatePeriod(Date beginDatePeriod) {
		this.beginDatePeriod = beginDatePeriod;
	}

	public Date getEndDatePeriod() {
		return endDatePeriod;
	}

	public void setEndDatePeriod(Date endDatePeriod) {
		this.endDatePeriod = endDatePeriod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public List<Exam> getExams() {
		return exams;
	}





}
