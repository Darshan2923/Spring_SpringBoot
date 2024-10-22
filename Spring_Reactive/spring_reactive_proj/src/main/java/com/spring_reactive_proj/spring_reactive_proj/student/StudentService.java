package com.spring_reactive_proj.spring_reactive_proj.student;

import java.time.Duration;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public Mono<Student> save(Student student) {
        return studentRepo.save(student);
    }

    public Flux<Student> findAll() {
        return studentRepo.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

    public Mono<Student> findById(Integer id) {
        return studentRepo.findById(id);
    }

}
