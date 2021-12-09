package com.gestion.exams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class PeriodViewController {

	@RequestMapping(value = "/period")
	public ModelAndView period() {
		return new ModelAndView("displayPeriod");
	}

}
