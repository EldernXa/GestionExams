package com.gestion.exams.dto;

import java.text.ParseException;
import java.util.Date;

import com.gestion.exams.services.DateService;

public class ExamDTO {

	private long idExam;
	private String nameRoom;
	private String beginDateExam;
	private String endDateExam;
	private PeriodDTO period;
	private int session;
	private String ue;
	private String[] supervisors;
	private int year;

	private String msgNotPlannedYet = "Pas planifiée pour l'instant";

	public Date getBeginDateExamInDateFormat() throws ParseException {
		if(beginDateExam.compareTo("")==0) {
			return null;
		}
		return DateService.convertStringDateToDateClass(beginDateExam);
	}

	public String getBeginDateExam() {
		if(this.beginDateExam.contentEquals("")) {
			return msgNotPlannedYet;
		}
		return this.beginDateExam;
	}

	public void setBeginDateExam(Date date) {
		if(date == null) {
			this.beginDateExam = "";
		}else {
			this.beginDateExam = DateService.convertDateClassToFullStringDate(date);
		}
	}

	public Date getEndDateExamInDateFormat() throws ParseException {
		if(endDateExam.compareTo("")==0) {
			return null;
		}
		return DateService.convertStringDateToDateClass(endDateExam);
	}

	public String getEndDateExam() {
		if(this.endDateExam.contentEquals("")) {
			return this.msgNotPlannedYet;
		}
		return this.endDateExam;
	}

	public void setEndDateExam(Date date) {
		if(date == null) {
			this.endDateExam = "";
		}else {
			this.endDateExam = DateService.convertDateClassToFullStringDate(date);
		}
	}

	public long getIdExam() {
		return idExam;
	}
	public void setIdExam(long idExam) {
		this.idExam = idExam;
	}
	public PeriodDTO getPeriod() {
		return period;
	}
	public void setPeriod(PeriodDTO period) {
		this.period = period;
	}
	public int getSession() {
		return session;
	}
	public void setSession(int session) {
		this.session = session;
	}
	public String getUe() {
		return ue;
	}
	public void setUe(String ue) {
		this.ue = ue;
	}
	public String[] getSupervisors() {
		return supervisors;
	}
	public void setSupervisors(String[] supervisors) {
		this.supervisors = supervisors;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getNameRoom() {
		return nameRoom;
	}
	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}

}
