package com.gestion.exams.dto;

public class ExamDTO {

	private long idExam;
	private PeriodDTO period;
	private int session;
	private String ue;
	private String[] supervisors;
	private int year;

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

}
