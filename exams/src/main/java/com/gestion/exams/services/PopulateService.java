package com.gestion.exams.services;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.entity.Authentification;
import com.gestion.exams.entity.Discipline;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.Inscription;
import com.gestion.exams.entity.Period;
import com.gestion.exams.entity.Room;
import com.gestion.exams.entity.Student;
import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.AuthentificationRepository;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.InscriptionRepository;
import com.gestion.exams.repository.PeriodRepository;
import com.gestion.exams.repository.RoomRepository;
import com.gestion.exams.repository.StudentRepository;
import com.gestion.exams.repository.UERepository;

@Service
public class PopulateService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UERepository ueRepository;

	@Autowired
	private PeriodRepository periodRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AuthentificationRepository authRepo;

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private InscriptionRepository inscriptionRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@PostConstruct
	public void populate() {
		populateRoom();
		populateUE();
		populatePeriod();
		populateStudent();
		populateAuthentification();
		populateExam();
		populateInscription();
		populateGrade();
	}

	private void populateRoom() {
		for(int i=0 ; i<10; i++) {
			Room room = new Room("A 0."+(i+1), 30);
			roomRepository.save(room);
		}
	}

	private void populateUE() {
		UE ue1 = new UE("Introduction à la programmation", 3, 3*60, Discipline.INFORMATIQUE);
		ueRepository.save(ue1);
		UE ue2 = new UE("Mathématique avancée", 6, 4*60, Discipline.MATH);
		ueRepository.save(ue2);
		UE ue3 = new UE("Génie Logiciel", 6, 2*60, Discipline.INFORMATIQUE);
		ueRepository.save(ue3);
		UE ue4 = new UE("Optique", 3, 2*60, Discipline.PHYSIQUE);
		ueRepository.save(ue4);
		UE ue5 = new UE("Badminton", 3, 2*60, Discipline.SPORT);
		ueRepository.save(ue5);
	}

	private void populatePeriod() {
		for(int i=0; i<2; i++) {
			String strBeginDate = "13/"+12+(i*5)+"/202"+(1+(i));
			String strEndDate = "17/"+12+(i*5)+"/202"+(1+(i));
			try {
				Period period = new Period(new SimpleDateFormat("dd/MM/yyyy").parse(strBeginDate),
						new SimpleDateFormat("dd/MM/yyyy").parse(strEndDate), "name"+i);
				periodRepository.save(period);
			}catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private void populateStudent() {
		for(int i=0; i<10; i++) {
			Student student = new Student("firstName"+i, "lastName"+i, "email"+i);
			studentRepository.save(student);
		}
	}

	private void populateAuthentification() {
		for(Student student : studentRepository.findAll()) {
			Authentification auth = new Authentification(student.getEmail(), "password", "student");
			authRepo.save(auth);
		}

		for(int i=0; i<4; i++) {
			Authentification auth = new Authentification("emailSchool"+i, "password2"+i, "education");
			authRepo.save(auth);
		}
	}

	private void populateExam() {
		List<Period> listPeriod = periodRepository.findAll();
		List<Room> listRoom = roomRepository.findAll();
		List<UE> listUE = ueRepository.findAll();
		for(int i = 0; i<listUE.size(); i++) {
			String strBeginDate = "13/"+12+(i*5)+"/202"+(1+(i));
			String strEndDate = "17/"+12+(i*5)+"/202"+(1+(i));
			try {
				Exam exam = new Exam(new SimpleDateFormat("dd/MM/yyyy").parse(strBeginDate), new SimpleDateFormat("dd/MM/yyyy").parse(strEndDate),
						1, 2021, listRoom.get(0), listPeriod.get(0), listUE.get(i));
				examRepository.save(exam);
			}catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private void populateInscription() {
		Student student = studentRepository.findAll().get(0);
		List<UE> listUE = ueRepository.findAll();
		for (UE element : listUE) {
			Inscription inscription = new Inscription(student, 2021, element);
			inscriptionRepository.save(inscription);
		}
	}

	private void populateGrade() {
		Student student = studentRepository.findAll().get(0);
		Random random = new Random();
		for(Exam exam : examRepository.findAll()) {
			Grade grade = new Grade(student, exam, random.nextInt()*20);
			gradeRepository.save(grade);
		}
	}

}

















