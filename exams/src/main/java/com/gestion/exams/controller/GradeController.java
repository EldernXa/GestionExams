package com.gestion.exams.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	ExamService examService;

	@GetMapping(path="/exam/{id}")
	public List<StudentDTO> getStudentsAndGradesByExam(@PathVariable("id") long idExam){
		List<StudentDTO> studentsDTO = new ArrayList<>();
		List<Student> students = studentService.getStudentsByExamId(idExam);
		for(Student s : students) {
			studentsDTO.add(StudentMapper.studentToStudentDTO(s, idExam));
		}
		return studentsDTO;
	}

	@PostMapping(path="/exams/{id}")
	public void updateAllGrades(@PathVariable("id") long idExam, @RequestBody List<Map<String, String>> mapStudent){
		System.out.println("SAVING GRADES OF EXAM "+idExam);
		for(Map<String, String> map : mapStudent){
			Grade g = new Grade(this.studentService.getStudentById(Long.parseLong(map.get("idStudent"))).get(),this.examService.getExamById(idExam).get(),Double.parseDouble(map.get("grade")));
			gradeService.createGrade(g);
		}
	}


	@PostMapping(path="/exam/{id}")
	public void updateGrade(@PathVariable("id") long idExam, @RequestBody Map<String, String> map){
		System.out.println("SAVING GRADE OF EXAM :"+idExam);
		Grade g = new Grade(this.studentService.getStudentById(Long.parseLong(map.get("idStudent"))).get(),this.examService.getExamById(idExam).get(),Double.parseDouble(map.get("grade")));
		gradeService.createGrade(g);
		//return g;
	}

	@DeleteMapping(path="/exam/{id}")
	public void deleteGrade(@PathVariable("id") long idExam, @RequestBody Grade g){
		gradeService.deleteGrade(g);
	}


}
