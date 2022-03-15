package com.gestion.exams.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.dto.mapper.GradeMapper;
import com.gestion.exams.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.exams.dto.StudentDTO;
import com.gestion.exams.dto.mapper.StudentMapper;
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
		/*
		List<StudentDTO> studentsDTO = new ArrayList<>();
		List<Student> students = studentService.getStudentsByExamId(idExam);
		for(Student s : students) {
			studentsDTO.add(StudentMapper.studentToStudentDTO(s, idExam));
		}
		return studentsDTO;
		*/
		List<GradeDTO> gradesDTO = new ArrayList<>();
		List<Grade> grades = gradeService.getGradesByExam(idExam);
		for(Grade g : grades) {
			gradesDTO.add(GradeMapper.gradeToGradeDTO(g));
			System.out.println("value of grade :"+g.getValue());
			System.out.println("grade dto value:"+gradesDTO.get(gradesDTO.size()-1).getValue());
			System.out.println("id exam of grade :"+g.getGradePK().getExam().getIdExam());
			System.out.println("grade dto value:"+gradesDTO.get(gradesDTO.size()-1).getIdExam());
			System.out.println();
		}
		return gradesDTO;

	}

	@GetMapping(path="/student")
	@PreAuthorize("hasAuthority('STUDENT')")
	public List<GradeDTO> getGradesOfStudent(Principal principal){
		Student student = studentRepository.getStudentByEmail(principal.getName());
		List<GradeDTO> gradesDTO = new ArrayList<>();
		List<Grade> grades = gradeService.getGradesByStudent(student.getIdStudent());
		//return grades;

		for(Grade g : grades)
			gradesDTO.add(GradeMapper.gradeToGradeDTO(g));
		System.out.println("nombre de notes de "+student.getIdStudent()+" : "+ grades.size());
		return gradesDTO;

	}

	@PostMapping(path="/exams/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void updateAllGrades(@PathVariable("id") long idExam, @RequestBody List<Map<String, String>> mapStudent){
		System.out.println("SAVING GRADES OF EXAM "+idExam);
		for(Map<String, String> map : mapStudent){
			Grade g = new Grade(this.studentService.getStudentById(Long.parseLong(map.get("idStudent"))).get(),this.examService.getExamById(idExam).get(),Double.parseDouble(map.get("grade")));
			gradeService.createGrade(g);
		}
	}


	@PostMapping(path="/exam/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void updateGrade(@PathVariable("id") long idExam, @RequestBody GradeDTO gradeDTO){
		Grade g = GradeMapper.gradeDTOToGrade(gradeDTO,gradeService);
		g.setValue(gradeDTO.getValue());
		System.out.println("SAVING GRADE OF EXAM :"+idExam + " / " + g.getGradePK().getExam().getIdExam());
		//Grade g = new Grade(this.studentService.getStudentById(Long.parseLong(map.get("idStudent"))).get(),this.examService.getExamById(idExam).get(),Double.parseDouble(map.get("grade")));
		gradeService.createGrade(g);
		//return g;
	}

	@DeleteMapping(path="/exam/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteGrade(@PathVariable("id") long idExam, @RequestBody Grade g){
		gradeService.deleteGrade(g);
	}


}
