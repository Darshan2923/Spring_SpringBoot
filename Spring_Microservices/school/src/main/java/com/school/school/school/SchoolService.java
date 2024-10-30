package com.school.school.school;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.school.school.client.StudentClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;
    private StudentClient client;

    public void saveSchool(School student) {
        repository.save(student);
    }

    public List<School> findAllSchools() {
        return repository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("Not_Found")
                                .email("Not_Found")
                                .build()

                );
        var students = client.findAllStudentsBySchool(schoolId); // find all the students from student micro-service
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .student(students)
                .build();
    }
}
