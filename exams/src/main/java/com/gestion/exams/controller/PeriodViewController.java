package com.gestion.exams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class PeriodViewController {

	@RequestMapping(value = "/periodDisplay")
	public ModelAndView period() {
		return new ModelAndView("displayPeriod");
	}

	@RequestMapping(value = "/periodSave")
	public ModelAndView newPeriod() {
		return new ModelAndView("formPeriod");
	}

}
