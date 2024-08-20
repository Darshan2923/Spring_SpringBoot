package com.myapp.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MyappApplication.class, args);
		Dev obj = context.getBean(Dev.class);
		obj.build();

	}

}
