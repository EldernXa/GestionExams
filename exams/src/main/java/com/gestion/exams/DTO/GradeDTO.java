package com.gestion.exams.dto;

import com.gestion.exams.entity.GradePK;


public class GradeDTO {

	/*
    @NotNull
    int idExam;

    @NotNull
    int idStudent;
	 */

	GradePK gradePK;

	double value;

	public void setValue(double value) {
		this.value = value;
	}
}
