package com.gestion.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gestion.exams.services.PopulateService;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = ExamsApplication.class)
@EntityScan(basePackageClasses = ExamsApplication.class)
@EnableTransactionManagement
public class ExamsApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExamsApplication.class, args);
	}

	PopulateService populateService;


	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	/*@Bean
	CommandLineRunner run(StudentRepository studentRepository, RoleRepository roleRepository, AuthentificationRepository authRepo){
		return args -> {
			Role studentRole = new Role ();
			studentRole.setName("STUDENT");
			studentRole=roleRepository.save(studentRole);
			//Role adminRole = new Role ();
			//adminRole.setName("ADMIN");
			//adminRole=roleRepository.save(adminRole);

			for(Student student : studentRepository.findAll()) {
				Authentification auth = new Authentification(student.getEmail(), "password", List.of(studentRole));
				authRepo.save(auth);
			}

			for(int i=0; i<4; i++) {
				Authentification auth = new Authentification("emailSchool"+i, "password2"+i, List.of(adminRole));
				authRepo.save(auth);
			}

			Authentification auth = new Authentification("test", "password", new ArrayList<>());
			authRepo.save(auth);
		};
	}*/


}
