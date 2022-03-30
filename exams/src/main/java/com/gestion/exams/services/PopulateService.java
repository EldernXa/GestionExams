package com.gestion.exams.services;


import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import com.gestion.exams.entity.*;
import com.gestion.exams.repository.*;
import com.github.javafaker.Faker;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

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

	@Autowired
	private UEService ueService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private PeriodService periodService;


	private SecureRandom random = new SecureRandom();

	@PostConstruct
	public void populate() {

		populateRoom();
		populateUE();
		populateStudent();
		populateAuthentification();
		/*
		populateInscription();
		populatePeriod();
		populateExam();
		populateGrade();
		 */
		for(int year=2020; year<=2022; year++){
			populateInscription(year);
			populatePeriod(year);
			populateExam(year,1);
			populateGrade(year,1);
			populateExam(year,2);
			populateGrade(year,2);
		}
		planifyPeriods();
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
		UE ue5 = new UE("Badminton", 1, 60, Discipline.SPORT);
		ueRepository.save(ue5);
		UE ue6 = new UE("Communication", 3, 2*60, Discipline.AUTRE);
		ueRepository.save(ue6);
	}

	private void populateStudent() {
		Faker faker = new Faker();
		for(int i=0; i<10; i++) {
			Student student = new Student(faker.name().firstName(), faker.name().lastName(), "student"+i+"@noteplus.fr");
			studentRepository.save(student);
		}
		Student student = new Student("Jean", "BADOGOLE", "jean-badogole@noteplus.fr");
		studentRepository.save(student);
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

	}

	private void populateInscription() {

		List<Student> students = studentRepository.findAll();

		List<UE> listUE = ueRepository.findAll();
		for(int year=2020 ; year<=2021; year++){
			for (UE element : listUE) {
				for(Student s : students) {
					if(random.nextBoolean()){
						Inscription inscription = new Inscription(s, year , element);
						inscriptionRepository.save(inscription);
					}
				}
			}
		}
		int countUe = 0;
		for (UE element : listUE) {
			int countStudent = 0;
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

	private void populateInscription(int year){
		List<Student> students = studentRepository.findAll();

		for(Student s : students) {
			if(!s.getLastName().equals("BADOGOLE")){
				List<UE> listUE = ueService.getSubscribeableInscriptionsOfStudent(s,year);
				for(UE ue : listUE){
					if(random.nextBoolean()){
						Inscription inscription = new Inscription(s, year , ue);
						inscriptionRepository.save(inscription);
					}
				}
			}
			else{
				if(year == 2020){
					Inscription inscription = new Inscription(s, year , ueService.getUeByName("Introduction à la programmation"));
					inscriptionRepository.save(inscription);
					inscription = new Inscription(s, year , ueService.getUeByName("Mathématique avancée"));
					inscriptionRepository.save(inscription);
				}
				if(year == 2021){
					Inscription inscription = new Inscription(s, year , ueService.getUeByName("Génie Logiciel"));
					inscriptionRepository.save(inscription);
					inscription = new Inscription(s, year , ueService.getUeByName("Mathématique avancée"));
					inscriptionRepository.save(inscription);
				}
				if(year == 2022){
					Inscription inscription = new Inscription(s, year , ueService.getUeByName("Optique"));
					inscriptionRepository.save(inscription);
					inscription = new Inscription(s, year , ueService.getUeByName("Communication"));
					inscriptionRepository.save(inscription);
				}
			}
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
				LOGGER.log(Logger.Level.valueOf("context"),exception);
			}
		}
	}

	private void populatePeriod(int year) {
			String strBeginDate1 = "01/01/"+year;
			String strEndDate1 = "15/01/"+year;
			String strBeginDate2 = "01/03/"+year;
			String strEndDate2 = "15/03/"+year;
			try {
				Period period1 = new Period(new SimpleDateFormat("dd/MM/yyyy").parse(strBeginDate1),
						new SimpleDateFormat("dd/MM/yyyy").parse(strEndDate1), "période session 1 "+year);
				periodRepository.save(period1);
				Period period2 = new Period(new SimpleDateFormat("dd/MM/yyyy").parse(strBeginDate2),
						new SimpleDateFormat("dd/MM/yyyy").parse(strEndDate2), "période session 2 "+year);
				periodRepository.save(period2);
			}catch(Exception exception) {
				LOGGER.log(Logger.Level.valueOf("context"),exception);
			}
	}

	private void populateExam() {
		List<Inscription> inscriptions = inscriptionRepository.findAll();
		for(Inscription i : inscriptions) {
				UE ue = i.getUe();
				int year = i.getYear();
				Period period = periodRepository.getPeriodByName("période "+year).get(0);
				List<Exam> exams = examRepository.searchExamsByUeAndYear(ue,year);
				if(exams.isEmpty() && (year != 2022 || period.getExams().size() < 2)) {
					Exam exam = new Exam(null, null, 1, year, /*listRoom.get(0)*/null, period, ue);
					examRepository.save(exam);
				}
		}
	}

	private void populateExam(int year, int session) {
		List<Inscription> inscriptions = inscriptionRepository.findAll();
		for(Inscription i : inscriptions) {
			if(i.getYear() == year && !periodRepository.getPeriodByName("période session "+session+ " " + year).isEmpty()) {
				UE ue = i.getUe();
				Period period = periodRepository.getPeriodByName("période session "+session+ " " + year).get(0);
				List<Exam> exams = examRepository.searchExamsByUeAndYear(ue, year);
				if (exams.isEmpty() || (exams.size()==1 && session == 2)) {
					Exam exam = new Exam(null, null, session, year, /*listRoom.get(0)*/null, period, ue);
					examRepository.save(exam);
				}
			}
		}
	}

	private void populateGrade() {

		List<Inscription> inscriptions = inscriptionRepository.findAll();

		for(Exam exam : examRepository.findAll()) {
			for(Inscription i : inscriptions){
				if(i.getUe().getName().equals(exam.getUe().getName()) && i.getYear() == exam.getYear()){
						Grade grade = new Grade(i.getStudent(),exam, random.nextInt((20 ) + 1));
						gradeRepository.save(grade);
				}
			}
		}
	}

	private void populateGrade(int year, int session) {

		List<Inscription> inscriptions = inscriptionRepository.findAll();
		for(Exam exam : examRepository.findAll()) {
			if(exam.getSession() == session && (year != 2022 || session == 1)) {
				for (Inscription i : inscriptions) {
					if (!i.getStudent().getLastName().equals("BADOGOLE")) {
						if (i.getUe().getName().equals(exam.getUe().getName()) && i.getYear() == exam.getYear() && i.getYear() == year) {
							if (session == 1 || (gradeService.getGradesMoreThan10ByStudentAndUE(i.getStudent().getIdStudent(), i.getUe().getName()).isEmpty() && session == 2)) {
								Grade grade = new Grade(i.getStudent(), exam, random.nextInt((20) + 1));
								gradeRepository.save(grade);
							}

						}
					} else {
						if (year == 2020 && i.getUe().getName().equals(exam.getUe().getName()) && i.getYear() == exam.getYear() && i.getYear() == year) {
							if (session == 1 && i.getUe().getName().equals("Introduction à la programmation")) {
								Grade grade = new Grade(i.getStudent(), exam, 15);
								gradeRepository.save(grade);
							}
							if (session == 1 && i.getUe().getName().equals("Mathématique avancée")) {
								Grade grade = new Grade(i.getStudent(), exam, 5);
								gradeRepository.save(grade);
							}
							if (session == 2 && i.getUe().getName().equals("Mathématique avancée")) {
								Grade grade = new Grade(i.getStudent(), exam, 9);
								gradeRepository.save(grade);
							}
						}
						if (year == 2021 && i.getUe().getName().equals(exam.getUe().getName()) && i.getYear() == exam.getYear() && i.getYear() == year) {
							if (session == 1 && i.getUe().getName().equals("Génie Logiciel")) {
								Grade grade = new Grade(i.getStudent(), exam, 18);
								gradeRepository.save(grade);
							}
							if (session == 1 && i.getUe().getName().equals("Mathématique avancée")) {
								Grade grade = new Grade(i.getStudent(), exam, 12);
								gradeRepository.save(grade);
							}
						}
						if (year == 2022 && i.getUe().getName().equals(exam.getUe().getName()) && i.getYear() == exam.getYear() && i.getYear() == year) {
							if (i.getUe().getName().equals("Optique")) {
								Grade grade = new Grade(i.getStudent(), exam, 5);
								gradeRepository.save(grade);
							}
							if (i.getUe().getName().equals("Communication")) {
								Grade grade = new Grade(i.getStudent(), exam, 15);
								gradeRepository.save(grade);
							}
						}
					}
				}
			}
		}
	}

	private void planifyPeriods(){
		List<Period> periods = periodRepository.findAll();
		for(Period period: periods){
			try {
				periodService.planRoomAndDateOfExams(period.getId());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}

















