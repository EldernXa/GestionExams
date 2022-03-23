package com.gestion.exams;

import com.gestion.exams.controller.*;
import com.gestion.exams.jpa.RoomRepositoryTest;
import com.gestion.exams.jpa.StudentRepositoryTest;
import com.gestion.exams.jpa.UeRepositoryTest;
import com.gestion.exams.services.*;
import org.junit.jupiter.api.Test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;

import org.junit.runner.JUnitCore;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;


@Suite.SuiteClasses({
		GradeServiceTest.class,
		UeServiceTest.class,
		DateServiceTest.class,
		ExamServiceTest.class,
		GradeServiceTest.class,
		PeriodServiceTest.class,
		RoomServiceTest.class,
		StudentServiceTest.class,
		AuthentificationControllerTest.class,
		ExamControllerTest.class,
		GradeControllerTest.class,
		InscriptionControllerTest.class,
		PeriodControllerTest.class,
		StudentControllerTest.class,
		UeControllerTest.class,
		RoomRepositoryTest.class,
		StudentRepositoryTest.class,
		UeRepositoryTest.class
})

@SelectPackages({"com.gestion.exams.controller", "com.gestion.exams.services", "com.gestion.exams.jpa"})
@RunWith(JUnitPlatform.class)

public class ExamsApplicationTests {

}
