package com.gestion.exams.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller()
public class VueInscriptionController {
    @RequestMapping(value = "/inscriptionView")
    private ModelAndView inscriptionOfStudent() {
        return new ModelAndView("inscription");
    }
}
