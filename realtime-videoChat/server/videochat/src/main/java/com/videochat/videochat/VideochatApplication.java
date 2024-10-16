package com.videochat.videochat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.videochat.videochat.user.User;
import com.videochat.videochat.user.UserService;

@SpringBootApplication
public class VideochatApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideochatApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			UserService service) {
		return args -> {
			service.register(User.builder()
					.username("Ali")
					.email("ali@mail.com")
					.password("aaa")
					.build());

			service.register(User.builder()
					.username("John")
					.email("john@mail.com")
					.password("aaa")
					.build());

			service.register(User.builder()
					.username("Anny")
					.email("anna@mail.com")
					.password("aaa")
					.build());
		};
	}

}
