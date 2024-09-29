package com.freecodecampspring.freecodecampspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.freecodecampspring.freecodecampspring.E_learning_app.models.Author;
import com.freecodecampspring.freecodecampspring.E_learning_app.repos.AuthorRepo;

@SpringBootApplication
public class FreecodecampspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreecodecampspringApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthorRepo repository) {
		return args -> {
			var author = Author.builder()
					.firstName("Darshan")
					.lastName("Patel")
					.age(20)
					.email("contact@darshan.com")
					.build();
			repository.save(author);
		};
	}
}
