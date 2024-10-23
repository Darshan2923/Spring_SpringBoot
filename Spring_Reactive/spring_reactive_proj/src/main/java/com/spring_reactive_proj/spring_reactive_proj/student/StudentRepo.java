package com.spring_reactive_proj.spring_reactive_proj.student;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface StudentRepo extends ReactiveCrudRepository<Student, Integer> {

    Flux<Student> findAllByFirstnameContainingIgnoreCase(String firstname);

}
