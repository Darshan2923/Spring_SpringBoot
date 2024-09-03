package com.freecodecampspring.freecodecampspring.school;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freecodecampspring.freecodecampspring.student.Student;

@RestController
public class SchoolController {
    private final SchoolRepo schoolRepo;

    public SchoolController(SchoolRepo schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    // Normal class repo
    // @PostMapping("/schools")
    // public School createSchool(@RequestBody School school) {
    // return schoolRepo.save(school);
    // }

    // By using school dto

    @PostMapping("/schools")
    public School createSchool(@RequestBody SchoolDto dto) {
        var school = new School();
        school.setName(dto.name());
        return schoolRepo.save(school);
    }

    private SchoolDto toSchoolDto(School school) {
        List<Integer> studentIds = school.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList());
        return new SchoolDto(school.getName(), studentIds);
    }

    @GetMapping("/schools")
    public List<SchoolDto> getAllSchools() {
        return schoolRepo.findAll().stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }

}
