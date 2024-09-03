package com.freecodecampspring.freecodecampspring.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepo extends JpaRepository<Student, Integer> {

    List<Student> findByLastName(String lastName);
}
