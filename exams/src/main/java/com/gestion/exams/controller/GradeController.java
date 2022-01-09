package com.gestion.exams.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.exams.dto.StudentDTO;
import com.gestion.exams.dto.mapper.StudentMapper;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Student;
import com.gestion.exams.services.ExamService;
import com.gestion.exams.services.GradeService;
import com.gestion.exams.services.StudentService;

@RestController
@Transactional
@RequestMapping("/grade")
public class GradeController {

	@Autowired
	GradeService gradeService;
	@Autowired
	StudentService studentService;
	@Autowired
	ExamService examService;

	@GetMapping(path="/exam{id}")
	public List<StudentDTO> getStudentsAndGradesByExam(@PathVariable("id") long idExam){
		List<StudentDTO> studentsDTO = new ArrayList<>();
		System.out.println(gradeService.getAllGrades().size());
		//gradeService.createAllGradesByExamIfNotExists(idExam);
		System.out.println(gradeService.getAllGrades().size());
		//System.out.println(gradeService.getAllGrades().get(0).getGradePK().getStudent().getIdStudent());
		List<Student> students = studentService.getStudentsByExamId(idExam);
		for(Student s : students) {
			System.out.println(s.getGrades().size());
			System.out.println("getting student : " + s.getIdStudent());
			studentsDTO.add(StudentMapper.studentToStudentDTO(s, idExam));
		}
		System.out.println("size dto "+studentsDTO.size());
		return studentsDTO;
	}

	@PostMapping(path="/exam{id}")
	public Grade addGrade(@PathVariable("id") long idExam, @RequestBody Grade g ){
		System.out.println("controller");
		gradeService.createGrade(g);
		return g;
	}

	@PostMapping(path="/exam{id}/create")
	public Grade createGrade(@PathVariable("id") long idExam, @RequestBody long idStudent){
		System.out.println(idStudent);
		Student student = studentService.getStudentById(idStudent).get();
		Exam exam = examService.getExamById(idExam).get();
		Grade g = new Grade(student,exam,0);
		gradeService.createGrade(g);
		return g;
	}

	@DeleteMapping(path="/exam{id}")
	public void deleteGrade(@PathVariable("id") long idExam, @RequestBody Grade g){
		gradeService.deleteGrade(g);
	}


}
