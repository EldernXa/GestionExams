package com.gestion.exams;

import com.gestion.exams.controller.*;
import com.gestion.exams.jpa.RoomRepositoryTest;
import com.gestion.exams.jpa.StudentRepositoryTest;
import com.gestion.exams.jpa.UeRepositoryTest;
import com.gestion.exams.services.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
		GradeServiceTest.class,
		UeServiceTest.class,
        GradeServiceTest.class,
        StudentServiceTest.class,
		AuthentificationControllerTest.class,
		ExamControllerTest.class,
		GradeControllerTest.class,
		InscriptionControllerTest.class,
        StudentControllerTest.class,
		UeControllerTest.class,
		RoomRepositoryTest.class,
		StudentRepositoryTest.class,
		UeRepositoryTest.class
})
public class ExamsMockitoTests {
}
