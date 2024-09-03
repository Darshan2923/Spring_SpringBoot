package com.freecodecampspring.freecodecampspring.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class StudentService {
    private final StudentsRepo repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentsRepo repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto post(@RequestBody StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.tostudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudent() {
        return repository.findAll()
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(@PathVariable("student-id") int id) {
        return repository.findById(id)
                .map(studentMapper::tostudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String name) {
        return repository.findByLastName(name)
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("students-id") int id) {
        repository.deleteById(id);
    }

}
