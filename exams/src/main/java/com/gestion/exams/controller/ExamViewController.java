package com.gestion.exams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExamViewController {

	@RequestMapping(value = "/exam")
	public ModelAndView listExams() {
		return new ModelAndView("examList");
	}

}
