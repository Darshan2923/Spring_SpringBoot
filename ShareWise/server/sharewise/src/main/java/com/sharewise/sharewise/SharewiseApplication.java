package com.sharewise.sharewise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import com.sharewise.sharewise.role.Role;
import com.sharewise.sharewise.role.RoleRepo;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class SharewiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharewiseApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepo roleRepo) {
		return args -> {
			if (roleRepo.findByName("USER").isEmpty()) {
				roleRepo.save(
						Role.builder().name("USER").build());
			}
		};
	}

}
