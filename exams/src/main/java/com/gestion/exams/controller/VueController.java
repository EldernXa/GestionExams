package com.gestion.exams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class VueController {
    @RequestMapping(value = "/ueView")
    public ModelAndView hello() {
        System.out.println("test");
        return new ModelAndView("UE");
    }
}
