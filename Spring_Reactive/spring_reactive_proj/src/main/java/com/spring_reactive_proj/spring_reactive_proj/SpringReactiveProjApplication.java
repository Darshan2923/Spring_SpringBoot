package com.spring_reactive_proj.spring_reactive_proj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring_reactive_proj.spring_reactive_proj.student.Student;
import com.spring_reactive_proj.spring_reactive_proj.student.StudentService;

@SpringBootApplication
public class SpringReactiveProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveProjApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			StudentService service) {
		return args -> {
			for (int i = 0; i < 100; i++) {
				service.save(Student
						.builder()
						.firstName("Darshan" + i)
						.lastName("Patel" + i)
						.age(i)
						.build())
						.subscribe();
			}
		};
	}

}
