package com.gestion.exams.services;


import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;

import com.gestion.exams.entity.*;
import com.gestion.exams.repository.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PopulateService{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

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



	private Random random = new Random();

	@PostConstruct
	public void populate() {
		populateRoom();
		populateUE();
		populateStudent();
		populateAuthentification();
		populateInscription();
		populatePeriod();
		populateExam();
		populateGrade();
	}

	private void populateRoom() {
		for(int i=0 ; i<10; i++) {
			Room room = new Room("A 0."+(i+1), 30);
			roomRepository.save(room);
		}
	}

	private void populateUE() {
		UE ue1 = new UE("Introduction à la programmation", 3, 3*60+30, Discipline.INFORMATIQUE);
		ueRepository.save(ue1);
		UE ue2 = new UE("Mathématique avancée", 3, 2*60+30, Discipline.MATH);
		ueRepository.save(ue2);
		UE ue3 = new UE("Génie Logiciel", 6, 3*60+30, Discipline.INFORMATIQUE);
		ueRepository.save(ue3);
		UE ue4 = new UE("Optique", 3, 2*60, Discipline.PHYSIQUE);
		ueRepository.save(ue4);
		UE ue5 = new UE("Badminton", 1, 1*60, Discipline.SPORT);
		ueRepository.save(ue5);
		UE ue6 = new UE("Communication", 3, 2*60, Discipline.AUTRE);
		ueRepository.save(ue6);
	}

	private void populateStudent() {
		Faker faker = new Faker();
		for(int i=0; i<10; i++) {
			Student student = new Student(faker.name().firstName()+i, faker.name().lastName()+i, "student"+i+"@noteplus.fr");
			studentRepository.save(student);
		}
	}



	private void populateAuthentification() {
		Role studentRole = new Role ();
		studentRole.setName("STUDENT");
		studentRole=roleRepository.save(studentRole);
		Role adminRole = new Role ();
		adminRole.setName("ADMIN");
		adminRole=roleRepository.save(adminRole);

		for(Student student : studentRepository.findAll()) {
			Authentification auth = new Authentification(student.getEmail(), passwordEncoder.encode("password"),List.of(studentRole));
			authRepo.save(auth);
		}

		for(int i=0; i<4; i++) {
			//school0@noteplus.fr password2
			Authentification auth = new Authentification("school"+i+"@noteplus.fr", passwordEncoder.encode("password2"), List.of(adminRole));
			authRepo.save(auth);
		}

		Authentification auth = new Authentification("test", passwordEncoder.encode("password3"), new ArrayList<>());
		authRepo.save(auth);
	}

	private void populateInscription() {
		/*
		Student student1 = studentRepository.findAll().get(0);
		Student student2 = studentRepository.findAll().get(1);
		Student student3 = studentRepository.findAll().get(2);
		 */
		List<Student> students = new ArrayList<>();
		students = studentRepository.findAll();
		/*
		students.add(student1);
		students.add(student2);
		students.add(student3);
		 */
		List<UE> listUE = ueRepository.findAll();
		Random r = new Random();
		for(int year=2017 ; year<=2021; year++){
			for (UE element : listUE) {
				for(Student s : students) {
					if(r.nextBoolean()){
						Inscription inscription = new Inscription(s, year , element);
						inscriptionRepository.save(inscription);
					}
				}
			}
		}
		int countUe = 0;
		int countStudent = 0;
		for (UE element : listUE) {
			for(Student s : students) {
				if(countUe >= 2 && countStudent > 0){
					Inscription inscription = new Inscription(s, 2022 , element);
					inscriptionRepository.save(inscription);
				}
				countStudent++;
			}
		countUe++;
		}
	}

	private void populatePeriod() {
		List<Integer> years = new ArrayList<>();
		for(Inscription i : inscriptionRepository.findAll())
			if(!years.contains(i.getYear()))
				years.add(i.getYear());
		for(int year : years) {
			String strBeginDate = "01/03/"+year;
			String strEndDate = "15/03/"+year;
			try {
				Period period = new Period(new SimpleDateFormat("dd/MM/yyyy").parse(strBeginDate),
						new SimpleDateFormat("dd/MM/yyyy").parse(strEndDate), "période "+year);
				periodRepository.save(period);
			}catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private void populateExam() {
		List<Period> listPeriod = periodRepository.findAll();
		List<Room> listRoom = roomRepository.findAll();
		List<Inscription> inscriptions = inscriptionRepository.findAll();
		for(int i = 0; i<inscriptions.size(); i++) {
				UE ue = inscriptions.get(i).getUe();
				int year = inscriptions.get(i).getYear();
				Period period = periodRepository.getPeriodByName("période "+year).get(0);
				List<Exam> exams = examRepository.searchExamsByUeAndYear(ue,year);
				if(exams.isEmpty() && (year != 2022 || period.getExams().size() < 5)) {
					Exam exam = new Exam(null, null, 1, year, /*listRoom.get(0)*/null, period, ue);
					examRepository.save(exam);
				}
		}
	}

	private void populateGrade() {
		/*
		Student student1 = studentRepository.findAll().get(0);
		Student student2 = studentRepository.findAll().get(1);
		Student student3 = studentRepository.findAll().get(2);
		 */
		List<Inscription> inscriptions = inscriptionRepository.findAll();
		List<Student> students = new ArrayList<>();
		students = studentRepository.findAll();
		/*
		students.add(student1);
		students.add(student2);
		students.add(student3);
		 */
		for(Exam exam : examRepository.findAll()) {
			//Grade grade = new Grade(student, exam, random.nextInt()*20);
			for(Inscription i : inscriptions){
				if(i.getUe().getName() == exam.getUe().getName() && i.getYear() == exam.getYear()){
					if(i.getYear()!=2022){
						Grade grade = new Grade(i.getStudent(),exam, random.nextInt((20 - 0) + 1 ) + 0);
						gradeRepository.save(grade);
					}
					List<Grade> grades = i.getStudent().getGrades();
					int count2022Grades = 0;
					for(Grade g : grades) {
						if (g.getGradePK().getExam().getYear() == i.getYear())
							count2022Grades++;
					}
					if(count2022Grades <= 2) {
						Grade grade = new Grade(i.getStudent(), exam, random.nextInt((20 - 0) + 1) + 0);
						gradeRepository.save(grade);
					}
				}
			}
			/*
			for(Student s : students) {
				Grade grade = new Grade(s, exam, random.nextInt((20 - 0) + 1 ) + 0);
				gradeRepository.save(grade);
			}
			 */
		}


	}

}

















