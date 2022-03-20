package com.gestion.exams;

import com.gestion.exams.controller.PeriodControllerTest;
import com.gestion.exams.controller.UeControllerTest;
import com.gestion.exams.services.GradeServiceTest;
import com.gestion.exams.services.UeServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
		GradeServiceTest.class,
		UeServiceTest.class
})

@SpringBootTest
class ExamsApplicationTests {

}
