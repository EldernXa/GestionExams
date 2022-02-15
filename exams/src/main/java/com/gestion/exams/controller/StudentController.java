package com.gestion.exams.controller;

import com.gestion.exams.entity.Authentification;
import com.gestion.exams.entity.Student;
import com.gestion.exams.repository.AuthentificationRepository;
import com.gestion.exams.repository.StudentRepository;
import com.gestion.exams.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AuthentificationRepository authentificationRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    @GetMapping("/auth")
    public ResponseEntity<List<Authentification>> getAuth(){
        return ResponseEntity.ok().body(authentificationRepository.findAll());
    }



	@Autowired
	private StudentService studentService;

	@Autowired
	private PeriodService periodService;

	@Autowired
	private ExamService examService;

	@GetMapping("/exams")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public ResponseEntity<List<ExamDTO>> getNextPeriodOfExam(Principal principal) {
		Student currentStudent = studentService.getStudentByEmail(principal.getName());
		Period nextPeriod = periodService.getNextPeriod();
		List<ExamDTO> listExam = examService.getAllExamsForAStudentFromPeriod(nextPeriod.getId(), currentStudent);

		return new ResponseEntity<>(listExam, HttpStatus.OK);
	}

}
