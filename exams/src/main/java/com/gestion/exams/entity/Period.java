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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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





}
