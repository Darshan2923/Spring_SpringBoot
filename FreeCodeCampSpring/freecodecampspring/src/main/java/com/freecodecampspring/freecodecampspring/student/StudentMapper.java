package com.freecodecampspring.freecodecampspring.student;

import org.springframework.stereotype.Service;

import com.freecodecampspring.freecodecampspring.school.School;

@Service
public class StudentMapper {
    public Student toStudent(StudentDto dto) {
        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        var school = new School();
        school.setId(dto.school_id());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDto tostudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
