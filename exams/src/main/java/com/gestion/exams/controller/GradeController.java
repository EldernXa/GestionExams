package com.gestion.exams.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.dto.mapper.GradeMapper;
import com.gestion.exams.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.GradeService;
import com.gestion.exams.services.StudentService;

@RestController
@Transactional
@RequestMapping("/grades")
@CrossOrigin(origins = "http://localhost:4200")
public class GradeController {

	@Autowired
	GradeService gradeService;
	@Autowired
	StudentService studentService;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ExamService examService;

	@GetMapping(path="/exam/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<GradeDTO> getGradesByExam(@PathVariable("id") long idExam){
		gradeService.createAllGradesByExamIfNotExists(idExam);
		List<GradeDTO> gradesDTO = new ArrayList<>();
		List<Grade> grades = gradeService.getGradesByExam(idExam);
		for(Grade g : grades) {
			gradesDTO.add(GradeMapper.gradeToGradeDTO(g));
		}
		return gradesDTO;

	}

	@GetMapping(path="/student")
	@PreAuthorize("hasAuthority('STUDENT')")
	public List<GradeDTO> getGradesOfStudent(Principal principal){
		Student student = studentRepository.getStudentByEmail(principal.getName());
		List<GradeDTO> gradesDTO = new ArrayList<>();
		List<Grade> grades = gradeService.getGradesByStudent(student.getIdStudent());
		for(Grade g : grades)
			gradesDTO.add(GradeMapper.gradeToGradeDTO(g));
		return gradesDTO;

	}

	@PostMapping(path="/exams/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void updateAllGrades(@PathVariable("id") long idExam, @RequestBody List<GradeDTO> gradesDTO){
		for(GradeDTO gDto : gradesDTO){
			Grade g = GradeMapper.gradeDTOToGrade(gDto,gradeService);
			if(g != null)
				gradeService.createGrade(g);
		}
	}


	@PostMapping(path="/exam/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void updateGrade(@PathVariable("id") long idExam, @RequestBody GradeDTO gradeDTO){
		Grade g = GradeMapper.gradeDTOToGrade(gradeDTO,gradeService);
		if(g != null){
			g.setValue(gradeDTO.getValue());
			gradeService.createGrade(g);
		}
	}

}
