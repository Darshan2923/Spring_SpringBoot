package com.sharewise.sharewise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SharewiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharewiseApplication.class, args);
	}

}
